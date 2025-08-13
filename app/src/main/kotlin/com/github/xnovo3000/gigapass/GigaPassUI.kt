package com.github.xnovo3000.gigapass

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.github.xnovo3000.gigapass.core.ui.GigaPassSurface
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.core.ui.GigaPassPopTransitionSpec
import com.github.xnovo3000.gigapass.core.ui.GigaPassPredictivePopTransitionSpec
import com.github.xnovo3000.gigapass.core.ui.GigaPassTransitionSpec
import com.github.xnovo3000.gigapass.feature.key.ui.KeyRoute
import com.github.xnovo3000.gigapass.feature.key.viewmodel.KeyViewModel
import com.github.xnovo3000.gigapass.feature.key.viewmodel.KeyViewModelFactory
import com.github.xnovo3000.gigapass.feature.keychain.ui.KeychainRoute
import kotlinx.serialization.Serializable

sealed class GigaPassRoute : NavKey {
    @Serializable data object Keychain : GigaPassRoute()
    @Serializable data class Key(val id: Long) : GigaPassRoute()
}

@Composable
fun GigaPassUI() {
    GigaPassTheme {
        GigaPassSurface {
            val backStack = rememberNavBackStack(GigaPassRoute.Keychain)
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryDecorators = listOf(
                    rememberSceneSetupNavEntryDecorator(),
                    rememberSavedStateNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                entryProvider = entryProvider {
                    entry<GigaPassRoute.Keychain> {
                        KeychainRoute(
                            viewModel = hiltViewModel(),
                            onGoToKeyPageClick = { backStack.add(GigaPassRoute.Key(it)) }
                        )
                    }
                    entry<GigaPassRoute.Key> { key ->
                        KeyRoute(
                            viewModel = hiltViewModel<KeyViewModel, KeyViewModelFactory>(
                                creationCallback = { viewModelFactory ->
                                    viewModelFactory.create(key.id)
                                }
                            ),
                            onBackClick = dropUnlessResumed { backStack.removeLastOrNull() }
                        )
                    }
                },
                transitionSpec = GigaPassTransitionSpec,
                popTransitionSpec = GigaPassPopTransitionSpec,
                predictivePopTransitionSpec = GigaPassPredictivePopTransitionSpec
            )
        }
    }
}