package com.github.xnovo3000.gigapass.feature.keychain.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.keychain.viewmodel.KeychainViewModel

@ExperimentalMaterial3Api
@Composable
fun KeychainRoute(
    viewModel: KeychainViewModel,
    onGoToNewKeyPageClick: () -> Unit
) {
    val lazyColumnState = rememberLazyListState()
    Scaffold(
        topBar = {

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
        KeychainContent(
            state = lazyColumnState,
            contentPadding = innerPadding,
            items = emptyList(),
            onItemClick = {}
        )
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@ExperimentalMaterial3Api
@Composable
private fun Preview() {
    GigaPassTheme {
        KeychainRoute(
            viewModel = KeychainViewModel(),
            onGoToNewKeyPageClick = {}
        )
    }
}