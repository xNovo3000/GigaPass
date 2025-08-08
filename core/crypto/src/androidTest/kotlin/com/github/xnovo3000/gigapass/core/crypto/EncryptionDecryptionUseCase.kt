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
    fun encryptAndDecryptSampleStringMustBeOk() = runBlocking {
        val sampleString = "my_test_string_1234"
        val encryptedValueResult = cryptoManager.encrypt(sampleString)
        Assert.assertTrue(encryptedValueResult.isSuccess)
        val decryptedValueResult = cryptoManager.decrypt(encryptedValueResult.getOrThrow())
        Assert.assertEquals(Result.success(sampleString), decryptedValueResult)
    }

    @Test
    fun tryToDecryptInvalidString() = runBlocking {
        val invalidString = "akfhSDFasd54=-afajfr234=="
        val encryptedValueResult = cryptoManager.decrypt(invalidString)
        Assert.assertTrue(encryptedValueResult.isFailure)
    }

}