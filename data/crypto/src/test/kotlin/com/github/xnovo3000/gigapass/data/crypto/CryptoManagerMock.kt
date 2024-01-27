package com.github.xnovo3000.gigapass.data.crypto

class CryptoManagerMock : CryptoManager {

    override fun encrypt(value: String): String {
        return value
    }

    override fun decrypt(value: String): String {
        return value
    }

}