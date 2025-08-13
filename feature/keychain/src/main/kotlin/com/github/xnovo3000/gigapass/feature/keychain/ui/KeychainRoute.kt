package com.github.xnovo3000.gigapass.feature.keychain.ui

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.xnovo3000.gigapass.feature.keychain.viewmodel.KeychainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeychainRoute(
    viewModel: KeychainViewModel,
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
            val coroutineScope = rememberCoroutineScope()
            val expanded by remember {
                derivedStateOf { lazyColumnState.firstVisibleItemIndex == 0 }
            }
            KeychainNewKeyFab(
                expanded = expanded,
                onClick = {
                    coroutineScope.launch { onGoToKeyPageClick(viewModel.createNew()) }
                }
            )
        }
    ) { innerPadding ->
        val items by viewModel.keys.collectAsStateWithLifecycle()
        KeychainContent(
            state = lazyColumnState,
            contentPadding = withNewKeyFabPadding(innerPadding),
            items = items,
            onItemClick = { onGoToKeyPageClick(it.id) }
        )
    }
}