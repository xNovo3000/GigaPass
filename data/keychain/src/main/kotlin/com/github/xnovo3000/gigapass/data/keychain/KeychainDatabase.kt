package com.github.xnovo3000.gigapass.data.keychain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.xnovo3000.gigapass.data.keychain.entity.KeyEntity
import com.github.xnovo3000.gigapass.data.keychain.repository.KeyRepository

@Database(entities = [KeyEntity::class], version = 1)
abstract class KeychainDatabase : RoomDatabase() {
    abstract fun getKeyRepository(): KeyRepository
}