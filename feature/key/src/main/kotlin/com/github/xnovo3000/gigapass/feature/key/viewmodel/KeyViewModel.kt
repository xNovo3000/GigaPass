package com.github.xnovo3000.gigapass.feature.key.viewmodel

import android.util.Log
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.xnovo3000.gigapass.core.crypto.CryptoManager
import com.github.xnovo3000.gigapass.data.keychain.entity.KeyEntity
import com.github.xnovo3000.gigapass.data.keychain.repository.KeyRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel(assistedFactory = KeyViewModelFactory::class)
class KeyViewModel @AssistedInject constructor(
    @Assisted private val keyId: Long,
    private val keyRepository: KeyRepository,
    private val cryptoManager: CryptoManager
) : ViewModel() {

    companion object {
        private const val TAG = "KeyViewModel"
    }

    var isIdValid: Boolean = false

    val websiteApplicationTextFieldState = TextFieldState()
    val usernameTextFieldState = TextFieldState()
    val passwordTextFieldState = TextFieldState()

    init { loadDefaults() }

    fun loadDefaults() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                // Get keyEntity
                val keyEntity = keyRepository.getById(keyId)
                if (keyEntity == null) {
                    Log.e(TAG, "Key with ID $keyId does not exists")
                    return@withContext
                }
                // Try to decrypt password
                val decryptedPasswordResult = cryptoManager.decrypt(keyEntity.encryptedPassword)
                if (decryptedPasswordResult.isFailure) {
                    Log.e(TAG, "Failed to decrypt the password. As a safety measure this key is not valid anymore")
                    return@withContext
                }
                // Set as valid entity
                isIdValid = true
                // Set default values
                websiteApplicationTextFieldState.setTextAndPlaceCursorAtEnd(keyEntity.serviceName)
                usernameTextFieldState.setTextAndPlaceCursorAtEnd(keyEntity.username)
                passwordTextFieldState.setTextAndPlaceCursorAtEnd(decryptedPasswordResult.getOrThrow())  // Will never throw
            }
        }
    }

    fun apply() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {

            }
        }
    }

}