package com.github.xnovo3000.gigapass.data.password.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "PasswordEntry",
    indices = [Index(name = "name", unique = true)]
)
data class PasswordEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val website: String,
    val principal: String,
    val encryptedPassword: String
)