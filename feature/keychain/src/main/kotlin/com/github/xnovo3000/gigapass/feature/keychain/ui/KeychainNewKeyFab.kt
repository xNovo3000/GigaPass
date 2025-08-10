package com.github.xnovo3000.gigapass.feature.keychain.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.keychain.R

@Composable
fun KeychainNewKeyFab(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onClick: () -> Unit
) {
    val newKeyText = stringResource(R.string.keychain_new_key)
    ExtendedFloatingActionButton(
        modifier = modifier,
        text = {
            Text(newKeyText)
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = newKeyText
            )
        },
        expanded = expanded,
        onClick = onClick
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    GigaPassTheme {
        var expanded by remember { mutableStateOf(true) }
        KeychainNewKeyFab(
            expanded = expanded,
            onClick = { expanded = !expanded }
        )
    }
}

@Composable
fun withNewKeyFabPadding(
    innerPadding: PaddingValues
): PaddingValues = PaddingValues(
    top = innerPadding.calculateTopPadding(),
    bottom = innerPadding.calculateBottomPadding() + 96.dp,
    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
)