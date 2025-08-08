package com.github.xnovo3000.gigapass

import androidx.compose.runtime.Composable
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
                    entry<GigaPassRoute.Keychain> {}
                    entry<GigaPassRoute.Key> {}
                }
            )
        }
    }
}