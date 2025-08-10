package com.github.xnovo3000.gigapass.data.keychain.repository

import androidx.room.Dao
import androidx.room.Query
import com.github.xnovo3000.gigapass.data.keychain.entity.KeyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KeyRepository {

    @Query("select * from KeyEntity order by serviceName asc")
    fun listenAll(): Flow<List<KeyEntity>>

    @Query("select * from KeyEntity where serviceName like '%' || :name || '%' order by serviceName asc")
    suspend fun getByServiceName(name: String): List<KeyEntity>

}