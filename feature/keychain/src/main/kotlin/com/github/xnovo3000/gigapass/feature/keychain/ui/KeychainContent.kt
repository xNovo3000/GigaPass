package com.github.xnovo3000.gigapass.feature.keychain.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme

@Composable
fun KeychainContent(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    colors: ListItemColors = ListItemDefaults.colors(),
    items: List<KeyItemData>,
    onItemClick: (KeyItemData) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding
    ) {
        items(
            items = items,
            key = { it.id }
        ) { keyItemData ->
            KeyItem(
                colors = colors,
                data = keyItemData,
                onClick = { onItemClick(keyItemData) }
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    GigaPassTheme {
        KeychainContent(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(0.dp),
            items = emptyList(),
            onItemClick = {}
        )
    }
}