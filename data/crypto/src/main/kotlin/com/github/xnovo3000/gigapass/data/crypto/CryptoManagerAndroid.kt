package com.github.xnovo3000.gigapass.data.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

internal class CryptoManagerAndroid : CryptoManager {

    companion object {
        private const val CIPHER_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val CIPHER_BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val CIPHER_ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val CIPHER_TRANSFORMATION = "$CIPHER_ALGORITHM/$CIPHER_BLOCK_MODE/$CIPHER_ENCRYPTION_PADDING"
        private const val CRYPTO_PROVIDER = "AndroidKeyStore"
        private const val KEY_PURPOSE = KeyProperties.PURPOSE_DECRYPT or KeyProperties.PURPOSE_ENCRYPT
        private const val KEY_NAME = "MASTER_KEY"
    }

    private val keyStore by lazy {
        KeyStore.getInstance(CRYPTO_PROVIDER).apply {
            load(null)
        }
    }

    private val secretKey by lazy {
        keyStore.getKey(KEY_NAME, null) as? SecretKey ?: generateMasterKey()
    }

    private val cipher by lazy {
        Cipher.getInstance(CIPHER_TRANSFORMATION)
    }

    override fun encrypt(value: String): String {
        // Run the action in a cipher-synchronized mode
        val byteArray = synchronized(cipher) {
            // Initialize cipher for encryption
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            // Encrypt
            val encryptedBytes = cipher.doFinal(value.toByteArray(Charset.defaultCharset()))
            // Push into a byte array
            ByteBuffer.allocate(4 + cipher.iv.size + encryptedBytes.size)
                .putInt(cipher.iv.size)
                .put(cipher.iv)
                .put(encryptedBytes)
                .array()
        }
        // Encode to base64
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    override fun decrypt(value: String): String {
        // Extract required values
        val byteBuffer = ByteBuffer.wrap(Base64.decode(value, Base64.DEFAULT))
        val ivSize = byteBuffer.getInt()
        val iv = ByteArray(ivSize)
        byteBuffer.get(iv, 0, ivSize)
        val encryptedBytesSize = byteBuffer.remaining()
        val encryptedBytes = ByteArray(encryptedBytesSize)
        byteBuffer.get(encryptedBytes, 0, encryptedBytesSize)
        // Run the action in a cipher-synchronized mode
        return synchronized(cipher) {
            // Initialize cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
            // Decrypt
            cipher.doFinal(encryptedBytes).toString(Charset.defaultCharset())
        }
    }

    private fun generateMasterKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(CIPHER_ALGORITHM, CRYPTO_PROVIDER)
        val keyParameters = KeyGenParameterSpec.Builder(KEY_NAME, KEY_PURPOSE)
            .setBlockModes(CIPHER_BLOCK_MODE)
            .setEncryptionPaddings(CIPHER_ENCRYPTION_PADDING)
            .setInvalidatedByBiometricEnrollment(false)
            .build()
        keyGenerator.init(keyParameters)
        return keyGenerator.generateKey()
    }

}