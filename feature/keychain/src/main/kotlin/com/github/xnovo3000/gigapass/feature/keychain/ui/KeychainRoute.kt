package com.github.xnovo3000.gigapass.feature.keychain.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.keychain.viewmodel.KeychainViewModel

@ExperimentalMaterial3ExpressiveApi
@ExperimentalMaterial3Api
@Composable
fun KeychainRoute(viewModel: KeychainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceContainer)
            .statusBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                bottom = 112.dp + WindowInsets.navigationBars.getBottom(LocalDensity.current).dp,
            )
        ) {
            item(key = -2) {
                ComponentTitle()
            }
            stickyHeader(key = -1) {
                ComponentSearch()
            }
            items(100) {
                Text(text = "Test $it")
            }
        }
        ComponentNewKeyFab(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .navigationBarsPadding(),
            onClick = {}
        )
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@ExperimentalMaterial3ExpressiveApi
@ExperimentalMaterial3Api
@Preview
@Composable
private fun Preview() {
    GigaPassTheme {
        KeychainRoute(
            viewModel = KeychainViewModel()
        )
    }
}