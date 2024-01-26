package com.github.xnovo3000.gigapass.data.password

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.xnovo3000.gigapass.data.password.dao.PasswordEntryDao
import com.github.xnovo3000.gigapass.data.password.entity.PasswordEntryEntity

@Database(entities = [PasswordEntryEntity::class], version = 1)
abstract class PasswordDatabase : RoomDatabase() {
    abstract fun getPasswordEntryDao(): PasswordEntryDao
}