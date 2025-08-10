package com.github.xnovo3000.gigapass.feature.keychain.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.keychain.domain.GetKeysByNameUseCase
import com.github.xnovo3000.gigapass.feature.keychain.domain.ListenKeysUseCase
import com.github.xnovo3000.gigapass.feature.keychain.viewmodel.KeychainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeychainRoute(
    viewModel: KeychainViewModel,
    onGoToNewKeyPageClick: () -> Unit,
    onGoToKeyPageClick: (Long) -> Unit
) {
    val lazyColumnState = rememberLazyListState()
    val scrollBehavior = SearchBarDefaults.enterAlwaysSearchBarScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            val searchBarState = rememberSearchBarState()
            val inputField = @Composable {
                KeychainTopBarInputField(
                    textFieldState = viewModel.searchTextFieldState,
                    searchBarState = searchBarState
                )
            }
            KeychainTopBarSearchBar(
                searchBarState = searchBarState,
                inputField = inputField,
                scrollBehavior = scrollBehavior
            )
            val searchItems by viewModel.searchKeys.collectAsStateWithLifecycle()
            KeychainTopBarSearchView(
                searchBarState = searchBarState,
                inputField = inputField,
                items = searchItems,
                onItemClick = {}
            )
        },
        floatingActionButton = {
            val expanded by remember {
                derivedStateOf { !lazyColumnState.isScrollInProgress }
            }
            KeychainNewKeyFab(
                expanded = expanded,
                onClick = onGoToNewKeyPageClick
            )
        }
    ) { innerPadding ->
        val items by viewModel.keys.collectAsStateWithLifecycle()
        KeychainContent(
            state = lazyColumnState,
            contentPadding = innerPadding,
            items = items,
            onItemClick = { onGoToKeyPageClick(it.id) }
        )
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Preview() {
    GigaPassTheme {
        KeychainRoute(
            viewModel = KeychainViewModel(
                listenKeysUseCase = ListenKeysUseCase(),
                getKeysByNameUseCase = GetKeysByNameUseCase()
            ),
            onGoToNewKeyPageClick = {},
            onGoToKeyPageClick = {}
        )
    }
}