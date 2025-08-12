package com.github.xnovo3000.gigapass.feature.key.domain

import android.util.Log
import com.github.xnovo3000.gigapass.core.crypto.CryptoManager
import com.github.xnovo3000.gigapass.data.keychain.repository.KeyRepository
import com.github.xnovo3000.gigapass.feature.key.dto.DecryptedKeyDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateKeyUseCase @Inject constructor(
    private val keyRepository: KeyRepository,
    private val cryptoManager: CryptoManager
) {

    companion object {
        private const val TAG = "UpdateKeyUseCase"
    }

    suspend operator fun invoke(
        decryptedKeyDto: DecryptedKeyDto
    ): Boolean = withContext(Dispatchers.Default) {
        // Make sure the key exists
        val keyEntity = keyRepository.getById(decryptedKeyDto.id)
        if (keyEntity == null) {
            Log.e(TAG, "Trying to update a non-existent key id ${decryptedKeyDto.id}")
            return@withContext false
        }
        // Try to encrypt the password
        val encryptedPasswordResult = cryptoManager.encrypt(decryptedKeyDto.password)
        if (encryptedPasswordResult.isFailure) {
            Log.e(TAG, "Failed to encrypt password", encryptedPasswordResult.exceptionOrNull())
            return@withContext false
        }
        // Update the entity
        val newKeyEntity = keyEntity.copy(
            serviceName = decryptedKeyDto.serviceName,
            username = decryptedKeyDto.username,
            encryptedPassword = encryptedPasswordResult.getOrThrow()  // Will never throw
        )
        keyRepository.update(newKeyEntity)
        true
    }

}