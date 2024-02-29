package com.github.xnovo3000.gigapass.data.password.dao

import androidx.room.Dao
import androidx.room.Query
import com.github.xnovo3000.gigapass.data.password.entity.PasswordEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordEntryDao {

    @Query("select * from PasswordEntry order by name asc")
    fun listenAll(): Flow<List<PasswordEntryEntity>>

}