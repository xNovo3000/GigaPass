package com.github.xnovo3000.gigapass.data.password

import android.content.Context
import androidx.room.Room
import com.github.xnovo3000.gigapass.data.password.dao.PasswordEntryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object PasswordRoomModule {

    @Provides
    @ActivityRetainedScoped
    fun providesPasswordDatabase(@ApplicationContext context: Context): PasswordDatabase {
        return Room
            .databaseBuilder(context, PasswordDatabase::class.java, "password.sql")
            .build()
    }

    @Provides
    @ActivityRetainedScoped
    fun providesPasswordEntryDao(passwordDatabase: PasswordDatabase): PasswordEntryDao {
        return passwordDatabase.getPasswordEntryDao()
    }

}