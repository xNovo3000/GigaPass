package com.github.xnovo3000.gigapass.feature.key.viewmodel

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.xnovo3000.gigapass.feature.key.domain.RetrieveDecryptedKeyUseCase
import com.github.xnovo3000.gigapass.feature.key.domain.UpdateKeyUseCase
import com.github.xnovo3000.gigapass.feature.key.dto.DecryptedKeyDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = KeyViewModelFactory::class)
class KeyViewModel @AssistedInject constructor(
    @Assisted private val keyId: Long,
    private val retrieveDecryptedKeyUseCase: RetrieveDecryptedKeyUseCase,
    private val updateKeyUseCase: UpdateKeyUseCase
) : ViewModel() {

    val websiteApplicationTextFieldState = TextFieldState()
    val usernameTextFieldState = TextFieldState()
    val passwordTextFieldState = TextFieldState()

    init { loadDefaults() }

    fun loadDefaults() {
        viewModelScope.launch {
            retrieveDecryptedKeyUseCase(id = keyId)?.let {
                websiteApplicationTextFieldState.setTextAndPlaceCursorAtEnd(it.serviceName)
                usernameTextFieldState.setTextAndPlaceCursorAtEnd(it.username)
                passwordTextFieldState.setTextAndPlaceCursorAtEnd(it.password)
            }
        }
    }

    fun apply() {
        viewModelScope.launch {
            updateKeyUseCase(
                decryptedKeyDto = DecryptedKeyDto(
                    id = keyId,
                    serviceName = websiteApplicationTextFieldState.text.toString(),
                    username = usernameTextFieldState.text.toString(),
                    password = passwordTextFieldState.text.toString()
                )
            )
        }
    }

}