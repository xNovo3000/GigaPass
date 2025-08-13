package com.github.xnovo3000.gigapass.feature.key.domain

import android.util.Log
import com.github.xnovo3000.gigapass.data.keychain.repository.KeyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteCurrentKeyUseCase @Inject constructor(
    private val keyRepository: KeyRepository
) {

    companion object {
        private const val TAG = "RetrieveDecryptedKeyUseCase"
    }

    suspend operator fun invoke(id: Long) = withContext(Dispatchers.Default) {
        // Make sure the key exists
        val keyEntity = keyRepository.getById(id)
        if (keyEntity == null) {
            Log.e(TAG, "Trying to delete a non-existent key id $id")
            return@withContext null
        }
        // Delete it
        keyRepository.delete(keyEntity)
    }

}