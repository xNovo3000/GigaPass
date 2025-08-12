package com.github.xnovo3000.gigapass.feature.key.domain

import android.util.Log
import com.github.xnovo3000.gigapass.core.crypto.CryptoManager
import com.github.xnovo3000.gigapass.data.keychain.repository.KeyRepository
import com.github.xnovo3000.gigapass.feature.key.dto.DecryptedKeyDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrieveDecryptedKeyUseCase @Inject constructor(
    private val keyRepository: KeyRepository,
    private val cryptoManager: CryptoManager
) {

    companion object {
        private const val TAG = "RetrieveDecryptedKeyUseCase"
    }

    suspend operator fun invoke(id: Long): DecryptedKeyDto? = withContext(Dispatchers.Default) {
        // Make sure the key exists
        val keyEntity = keyRepository.getById(id)
        if (keyEntity == null) {
            Log.e(TAG, "Trying to update a non-existent key id $id")
            return@withContext null
        }
        // Try to decrypt the password
        val decryptedPasswordResult = cryptoManager.decrypt(keyEntity.encryptedPassword)
        if (decryptedPasswordResult.isFailure) {
            Log.w(TAG, "Failed to decrypt password", decryptedPasswordResult.exceptionOrNull())
        }
        // Build the DTO
        DecryptedKeyDto(
            id = keyEntity.id,
            serviceName = keyEntity.serviceName,
            username = keyEntity.username,
            password = decryptedPasswordResult.getOrDefault("")
        )
    }

}