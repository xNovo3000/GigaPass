package com.github.xnovo3000.gigapass.data.crypto

import android.util.Log
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4ClassRunner::class)
class CryptoManagerTest {

    companion object {
        const val TAG = "CryptoManagerTest"
        const val TEST_VALUE = "MyUltraSecretPassword"
        const val TEST_TIMES = 20
    }

    private lateinit var cryptoManager: CryptoManager

    @Before
    fun init() {
        cryptoManager = CryptoManagerAndroid()
    }

    @Test
    fun encryptAndDecryptStringMultiSync() {
        // Execute N times
        for (i in 0 until TEST_TIMES) {
            // Test
            encryptAndDecryptString("$TEST_VALUE$i")
        }
    }

    @Test
    fun encryptAndDecryptStringMultiAsync() {
        // Create code to execute asynchronously
        val executions = List(TEST_TIMES) {
            Runnable { encryptAndDecryptString("$TEST_VALUE$it") }
        }
        // Create thread pool
        val executorService = Executors.newFixedThreadPool(TEST_TIMES)
        // Launch executions
        executions.forEach { executorService.submit(it) }
        // Wait for termination
        executorService.shutdown()
        while (!executorService.isTerminated) {
            Thread.sleep(1000)
        }
    }

    private fun encryptAndDecryptString(value: String) {
        // Encrypt
        val encryptedValue = cryptoManager.encrypt(value)
        // Decrypt
        val decryptedValue = cryptoManager.decrypt(encryptedValue)
        // Check
        Truth.assertThat(decryptedValue).isEqualTo(value)
        Log.d(TAG, "Execution with value $value ok")
    }

}