package com.github.xnovo3000.gigapass.feature.keychain.viewmodel

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.xnovo3000.gigapass.feature.keychain.domain.GetKeysByNameUseCase
import com.github.xnovo3000.gigapass.feature.keychain.domain.ListenKeysUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class KeychainViewModel @Inject constructor(
    listenKeysUseCase: ListenKeysUseCase,
    private val getKeysByNameUseCase: GetKeysByNameUseCase
) : ViewModel() {

    val keys = listenKeysUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val searchTextFieldState = TextFieldState()
    @OptIn(FlowPreview::class) val searchKeys = snapshotFlow { searchTextFieldState.text }
        .debounce(150.milliseconds)
        .map { getKeysByNameUseCase(it.toString()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

}