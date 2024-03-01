package com.github.xnovo3000.gigapass.feature.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.xnovo3000.gigapass.core.ui.theme.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.home.model.PasswordEntry

sealed interface HomeScreenSearchState {
    data object Closed : HomeScreenSearchState
    data class Open(val searchString: String) : HomeScreenSearchState
}

data class HomeScreenState(
    val entries: List<PasswordEntry>,
    val search: HomeScreenSearchState
)

sealed interface HomeScreenClick {
    data object Search : HomeScreenClick
    data object New : HomeScreenClick
    data class Element(val id: Int) : HomeScreenClick
}

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onClick: (HomeScreenClick) -> Unit
) {
    // Get padding values
    val density = LocalDensity.current
    val systemBarsInsets = WindowInsets.systemBars
    val backgroundColor = MaterialTheme.colorScheme.background
    val contentInsets = remember(systemBarsInsets) {
        PaddingValues(
            top = systemBarsInsets.getTop(density).dp,
            bottom = systemBarsInsets.getBottom(density).dp
        )
    }
    // Build the scaffold
    Box(modifier = Modifier.fillMaxSize()) {
        // Content
        // FAB
        // Expandable search bar
        // Status bar color
        Spacer(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(systemBarsInsets.getTop(density).dp)
                .background(color = backgroundColor.copy(alpha = 0.75F))
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    GigaPassTheme {
        HomeScreen(
            state = HomeScreenState(
                entries = emptyList(),
                search = HomeScreenSearchState.Closed
            ),
            onClick = {}
        )
    }
}