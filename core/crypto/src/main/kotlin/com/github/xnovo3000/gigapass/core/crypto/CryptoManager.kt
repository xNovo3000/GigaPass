package com.github.xnovo3000.gigapass.core.crypto

interface CryptoManager {
    suspend fun decrypt(value: String): Result<String>
    suspend fun encrypt(value: String): String
}