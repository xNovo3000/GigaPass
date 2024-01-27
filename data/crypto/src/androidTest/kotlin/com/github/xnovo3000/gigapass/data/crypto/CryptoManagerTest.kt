package com.github.xnovo3000.gigapass.data.crypto

import android.util.Log
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CryptoManagerTest {

    companion object {
        const val VALUE = "MyUltraSecretPassword1"
        const val TAG = "CryptoManagerTest"
    }

    private lateinit var cryptoManager: CryptoManager

    @Before
    fun init() {
        cryptoManager = CryptoManagerAndroid()
    }

    @Test
    fun encryptAndDecryptString() {
        val encryptedValue = cryptoManager.encrypt(VALUE)
        Log.d(TAG, "encryptedValue: $encryptedValue")
        val decryptedValue = cryptoManager.decrypt(encryptedValue)
        Truth.assertThat(decryptedValue).isEqualTo(VALUE)
    }

}