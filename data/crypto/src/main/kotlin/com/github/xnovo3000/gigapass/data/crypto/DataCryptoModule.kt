package com.github.xnovo3000.gigapass.data.crypto

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataCryptoModule {

    @Provides
    @ActivityRetainedScoped
    fun providesCryptoManager(): CryptoManager {
        return CryptoManagerAndroid()
    }

}