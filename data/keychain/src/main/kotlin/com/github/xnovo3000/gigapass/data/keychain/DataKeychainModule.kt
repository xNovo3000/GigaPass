package com.github.xnovo3000.gigapass.data.keychain

import android.content.Context
import androidx.room.Room
import com.github.xnovo3000.gigapass.data.keychain.repository.KeyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class DataKeychainModule {

    @Provides
    @ActivityRetainedScoped
    fun providesKeychainDatabase(@ApplicationContext context: Context): KeychainDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = KeychainDatabase::class.java,
            name = "keychain.db"
        ).build()
    }

    @Provides
    @ActivityRetainedScoped
    fun providesKeyRepository(keychainDatabase: KeychainDatabase): KeyRepository {
        return keychainDatabase.getKeyRepository()
    }

}