package com.github.xnovo3000.gigapass.feature.key.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.key.R

@Composable
fun KeyListItemWebsiteApplication(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "A",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        // TODO: Switch to BasicTextField
        Text(
            modifier = Modifier.weight(1F),
            text = "Amazon",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CredentialsPreview() {
    GigaPassTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceContainer) {
            KeyListItemWebsiteApplication()
        }
    }
}

@Composable
fun KeyListItemUsername(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(bottom = 1.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 20.dp, topEnd = 20.dp,
                    bottomStart = 4.dp, bottomEnd = 4.dp
                )
            )
            .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // TODO: Switch to BasicTextField
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Amazon",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun KeyListItemPassword(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(bottom = 1.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 4.dp, topEnd = 4.dp,
                    bottomStart = 20.dp, bottomEnd = 20.dp
                )
            )
            .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(start = 16.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // TODO: Switch to BasicTextField
        Text(
            modifier = Modifier.weight(1F),
            text = "Test",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        FilledTonalIconButton(
            modifier = Modifier.minimumInteractiveComponentSize()
                .size(
                    size = IconButtonDefaults.extraSmallContainerSize(
                        widthOption = IconButtonDefaults.IconButtonWidthOption.Wide
                    )
                ),
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.size(IconButtonDefaults.extraSmallIconSize),
                imageVector = Icons.Rounded.Visibility,
                contentDescription = stringResource(R.string.key_top_back)
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun UsernamePasswordPreview() {
    GigaPassTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceContainer) {
            Column {
                KeyListItemUsername()
                KeyListItemPassword()
            }
        }
    }
}