package com.github.xnovo3000.gigapass.feature.key.dto

data class DecryptedKeyDto(
    val id: Long,
    val serviceName: String,
    val username: String,
    val password: String
)