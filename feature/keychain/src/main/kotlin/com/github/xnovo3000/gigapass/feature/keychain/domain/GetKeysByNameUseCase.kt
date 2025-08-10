package com.github.xnovo3000.gigapass.feature.keychain.domain

import com.github.xnovo3000.gigapass.feature.keychain.ui.KeyItemData
import javax.inject.Inject

class GetKeysByNameUseCase @Inject constructor() {

    suspend operator fun invoke(value: String): List<KeyItemData> {
        return List(100) {
            KeyItemData(
                id = it.toLong(),
                name = "Amazon $it"
            )
        }
    }

}