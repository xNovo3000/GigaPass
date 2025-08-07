package com.github.xnovo3000.gigapass.core.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec
import kotlin.io.encoding.Base64

internal class CryptoManagerAndroid(coroutineScope: CoroutineScope) : CryptoManager {

    companion object {
        private const val TAG = "CryptoManagerAndroid"
        private const val KEYSTORE_PROVIDER = "AndroidKeyStore"
        private const val KEY_ALIAS = "com.github.xnovo3000.gigapass"
        private const val KEY_PURPOSES = KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        private const val KEY_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val KEY_BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val KEY_PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
    }

    private val secretKeyDeferred = coroutineScope.async {
        // Get KeyStore
        val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
        keyStore.load(null)
        // Retrieve the master key or create one if not present
        if (keyStore.containsAlias(KEY_ALIAS)) {
            Log.d(TAG, "Key $KEY_ALIAS exists, retrieving from $KEYSTORE_PROVIDER")
            val entry = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.SecretKeyEntry
            entry.secretKey
        } else {
            Log.d(TAG, "Key $KEY_ALIAS does not exists, generating new one with transformer " +
                    "$KEY_ALGORITHM/$KEY_BLOCK_MODE/$KEY_PADDING into $KEYSTORE_PROVIDER")
            val keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM, KEYSTORE_PROVIDER)
            val spec = KeyGenParameterSpec.Builder(KEY_ALIAS, KEY_PURPOSES)
                .setBlockModes(KEY_BLOCK_MODE)
                .setEncryptionPaddings(KEY_PADDING)
                .build()
            keyGenerator.init(spec)
            keyGenerator.generateKey()
        }
    }

    override suspend fun decrypt(value: String): Result<String> {
        Log.v(TAG, "Trying to decrypt '$value' using $KEY_ALIAS")
        // The value must contain an IV and an encrypted value both encoded
        // in Base64 and separated using '-' (not a Base64 character)
        val iv = try {
            Base64.Default.decode(value.substringBefore('-', ""))
        } catch (e: Exception) {
            return Result.failure<String>(e)
        }
        val encryptedData = try {
            Base64.Default.decode(value.substringAfter('-', ""))
        } catch (e: Exception) {
            return Result.failure<String>(e)
        }
        // Await for key being generated or retrieved
        val secretKey = secretKeyDeferred.await()
        // Cipher is not thread-safe, so the operations must be done one at a time
        val result = synchronized(this) {
            val cipher = Cipher.getInstance("$KEY_ALGORITHM/$KEY_BLOCK_MODE/$KEY_PADDING")
            cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
            cipher.doFinal(encryptedData)
        }
        // Convert the result into a String
        return Result.success(result.toString(Charsets.UTF_8))
    }

    override suspend fun encrypt(value: String): String {
        Log.v(TAG, "Encrypting '$value' using $KEY_ALIAS")
        // Await for key being generated or retrieved
        val secretKey = secretKeyDeferred.await()
        // Cipher is not thread-safe, so the operations must be done one at a time
        val (iv, encryptedData) = synchronized(this) {
            val cipher = Cipher.getInstance("$KEY_ALGORITHM/$KEY_BLOCK_MODE/$KEY_PADDING")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            val encryptedBytes = cipher.doFinal(value.toByteArray())
            Pair(cipher.iv, encryptedBytes)
        }
        // Save them in Base64 and return the completed String
        return "${Base64.Default.encode(iv)}-${Base64.Default.encode(encryptedData)}"
    }

}