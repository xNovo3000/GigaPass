package com.github.xnovo3000.gigapass.data.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

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

    override fun encrypt(value: String): String {
        // Get KeyStore
        val keyStore = loadKeyStore()
        // Get master key, or generate one if missing
        val key = keyStore.getKey(KEY_NAME, null) as? SecretKey ?: generateMasterKey()
        // Initialize the cipher
        val cipher = createCipher()
        cipher.init(Cipher.ENCRYPT_MODE, key)
        // Encrypt
        return cipher.doFinal(value.toByteArray(Charsets.UTF_8)).toString(Charsets.UTF_8)
    }

    override fun decrypt(value: String): String {
        // Get KeyStore
        val keyStore = loadKeyStore()
        // Get master key, or generate one if missing
        val key = keyStore.getKey(KEY_NAME, null) as? SecretKey ?: generateMasterKey()
        // Initialize the cipher
        val cipher = createCipher()
        cipher.init(Cipher.DECRYPT_MODE, key)
        // Decrypt
        return cipher.doFinal(value.toByteArray(Charsets.UTF_8)).toString(Charsets.UTF_8)
    }

    private fun loadKeyStore(): KeyStore {
        val keyStore = KeyStore.getInstance(CRYPTO_PROVIDER)
        keyStore.load(null)
        return keyStore
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

    private fun createCipher(): Cipher {
        return Cipher.getInstance(CIPHER_TRANSFORMATION, CRYPTO_PROVIDER)
    }

}