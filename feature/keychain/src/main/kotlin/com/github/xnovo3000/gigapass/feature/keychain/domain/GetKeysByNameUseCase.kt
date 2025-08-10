package com.github.xnovo3000.gigapass.feature.keychain.domain

import com.github.xnovo3000.gigapass.data.keychain.repository.KeyRepository
import com.github.xnovo3000.gigapass.feature.keychain.ui.KeyItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetKeysByNameUseCase @Inject constructor(
    private val keyRepository: KeyRepository
) {

    suspend operator fun invoke(
        name: String
    ): List<KeyItemData> = withContext(Dispatchers.Default) {
        keyRepository.getByServiceName(name)
            .map { KeyItemData(id = it.id, name = it.serviceName) }
    }

}