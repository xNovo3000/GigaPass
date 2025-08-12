package com.github.xnovo3000.gigapass.feature.keychain.domain

import com.github.xnovo3000.gigapass.data.keychain.entity.KeyEntity
import com.github.xnovo3000.gigapass.data.keychain.repository.KeyRepository
import javax.inject.Inject

class CreateKeyUseCase @Inject constructor(
    private val keyRepository: KeyRepository
) {

    suspend operator fun invoke(): KeyEntity {
        val newKeyEntity = KeyEntity(
            id = 0,
            serviceName = "",
            username = "",
            encryptedPassword = ""
        )
        keyRepository.insert(newKeyEntity)
        return newKeyEntity
    }

}