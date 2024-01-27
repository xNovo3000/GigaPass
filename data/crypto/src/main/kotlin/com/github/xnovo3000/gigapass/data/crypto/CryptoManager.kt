package com.github.xnovo3000.gigapass.data.crypto

interface CryptoManager {
    fun encrypt(value: String): String
    fun decrypt(value: String): String
}