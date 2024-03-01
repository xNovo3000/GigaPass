package com.github.xnovo3000.gigapass.feature.home.domain

import android.util.Log
import com.github.xnovo3000.gigapass.core.algorithm.obscurer.StringObscurer
import com.github.xnovo3000.gigapass.data.password.dao.PasswordEntryDao
import com.github.xnovo3000.gigapass.feature.home.model.PasswordEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPasswordEntriesUseCase @Inject constructor(
    private val passwordEntryDao: PasswordEntryDao,
    private val stringObscurer: StringObscurer
) {

    companion object {
        private const val TAG = "GetPasswordEntries"
    }

    operator fun invoke(): Flow<List<PasswordEntry>> {
        return passwordEntryDao.listenAll()
            .map { list ->
                Log.v(TAG, "Received update from Flow")
                list.map {
                    PasswordEntry(
                        id = it.id,
                        name = it.name,
                        obscuredPrincipal = stringObscurer.obscure(it.principal)
                    )
                }
            }
    }

}