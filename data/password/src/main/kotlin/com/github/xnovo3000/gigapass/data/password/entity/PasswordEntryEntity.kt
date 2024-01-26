package com.github.xnovo3000.gigapass.data.password.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PasswordEntry")
data class PasswordEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val website: String,
    val email: String,
    val password: String
)