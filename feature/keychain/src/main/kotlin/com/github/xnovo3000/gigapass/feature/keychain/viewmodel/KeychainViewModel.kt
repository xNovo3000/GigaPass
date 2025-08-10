package com.github.xnovo3000.gigapass.feature.keychain.viewmodel

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KeychainViewModel @Inject constructor() : ViewModel() {

    val searchTextFieldState = TextFieldState()

}