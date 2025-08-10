package com.github.xnovo3000.gigapass.feature.keychain.domain

import com.github.xnovo3000.gigapass.data.keychain.repository.KeyRepository
import com.github.xnovo3000.gigapass.feature.keychain.ui.KeyItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListenKeysUseCase @Inject constructor(
    private val keyRepository: KeyRepository
) {

    operator fun invoke(): Flow<List<KeyItemData>> {
        return keyRepository.listenAll()
            .map { keyEntities ->
                withContext(Dispatchers.Default) {
                    keyEntities.map { KeyItemData(id = it.id, name = it.serviceName) }
                }
            }
    }

}