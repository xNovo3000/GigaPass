package com.github.xnovo3000.gigapass.core.crypto

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class EncryptionDecryptionUseCase {

    private val cryptoManager: CryptoManager = CryptoManagerAndroid(
        coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    )

    @Test
    fun encryptAndDecryptSampleStringMustBeOk() {
        val sampleString = "my_test_string_1234"
        val result = runBlocking {
            val encryptedValue = cryptoManager.encrypt(sampleString)
            cryptoManager.decrypt(encryptedValue)
        }
        Assert.assertEquals(Result.success(sampleString), result)
    }

}