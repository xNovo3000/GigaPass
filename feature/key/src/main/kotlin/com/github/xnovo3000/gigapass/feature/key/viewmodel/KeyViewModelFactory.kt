package com.github.xnovo3000.gigapass.feature.key.viewmodel

import dagger.assisted.AssistedFactory

@AssistedFactory
interface KeyViewModelFactory {
    fun create(keyId: Long): KeyViewModel
}