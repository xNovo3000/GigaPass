package com.github.xnovo3000.gigapass.feature.keychain.domain

import com.github.xnovo3000.gigapass.feature.keychain.ui.KeyItemData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListenKeysUseCase @Inject constructor() {

    operator fun invoke(): Flow<List<KeyItemData>> {
        return flow {
            emit(
                value = List(100) {
                    KeyItemData(
                        id = it.toLong(),
                        name = "Amazon $it"
                    )
                }
            )
        }
    }

}