package com.github.xnovo3000.gigapass.core.crypto

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(ActivityRetainedScoped::class)
class CoreCryptoModule {

    @Provides
    @ActivityRetainedScoped
    fun providesCryptoManager(): CryptoManager {
        return CryptoManagerAndroid(
            coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        )
    }

}