package com.github.xnovo3000.gigapass.data.keychain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KeyEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val serviceName: String,
    val username: String,
    val encryptedPassword: String
)