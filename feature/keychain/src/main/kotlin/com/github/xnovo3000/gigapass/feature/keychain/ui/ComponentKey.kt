package com.github.xnovo3000.gigapass.feature.keychain.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme

sealed class ComponentKeyData {
    data class Header(
        val letter: String,
        val isFirst: Boolean
    ) : ComponentKeyData()
    data class Item(
        val id: Long,
        val serviceName: String,
        val isFirst: Boolean,
        val isLast: Boolean
    ) : ComponentKeyData()
}

@Composable
fun ComponentKeyHeader(
    modifier: Modifier = Modifier,
    header: ComponentKeyData.Header
) {
    val topPadding = if (header.isFirst) 8.dp else 16.dp
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceContainer)
            .padding(horizontal = 24.dp)
            .padding(top = topPadding, bottom = 8.dp)
    ) {
        Text(
            text = header.letter,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun ComponentKeyItem(
    modifier: Modifier = Modifier,
    item: ComponentKeyData.Item,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceContainer
    ) {
        val shape = RoundedCornerShape(
            topStart = if (item.isFirst) 20.dp else 4.dp,
            topEnd = if (item.isFirst) 20.dp else 4.dp,
            bottomStart = if (item.isLast) 20.dp else 4.dp,
            bottomEnd = if (item.isLast) 20.dp else 4.dp,
        )
        val bottomPadding = if (!item.isLast) 2.dp else 0.dp
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 16.dp)
                .padding(bottom = bottomPadding)
                .clip(shape)
                .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.serviceName.first().uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Text(
                text = item.serviceName,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHeader() {
    GigaPassTheme {
        ComponentKeyHeader(
            header = ComponentKeyData.Header(
                letter = "A",
                isFirst = true
            )
        )
    }
}

@Preview
@Composable
private fun PreviewData() {
    GigaPassTheme {
        ComponentKeyItem(
            item = ComponentKeyData.Item(
                id = 1,
                serviceName = "Test",
                isFirst = true,
                isLast = true
            ),
            onClick = {}
        )
    }
}