package com.github.xnovo3000.gigapass.core.algorithm

import com.github.xnovo3000.gigapass.core.algorithm.obscurer.StringObscurer
import com.github.xnovo3000.gigapass.core.algorithm.obscurer.StringObscurerAndroid
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object CoreAlgorithmModule {

    @Provides
    fun providesStringObscurer(): StringObscurer {
        return StringObscurerAndroid()
    }

}