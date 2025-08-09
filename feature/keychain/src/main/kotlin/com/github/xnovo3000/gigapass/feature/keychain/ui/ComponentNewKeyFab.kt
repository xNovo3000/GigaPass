package com.github.xnovo3000.gigapass.feature.keychain.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.keychain.R

@ExperimentalMaterial3ExpressiveApi
@Composable
fun ComponentNewKeyFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    MediumFloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = stringResource(R.string.keychain_new_key)
        )
    }
}

@ExperimentalMaterial3ExpressiveApi
@Preview
@Composable
private fun Preview() {
    GigaPassTheme {
        ComponentNewKeyFab(onClick = {})
    }
}