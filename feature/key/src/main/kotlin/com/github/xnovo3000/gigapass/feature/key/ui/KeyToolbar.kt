package com.github.xnovo3000.gigapass.feature.key.ui

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.key.R

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun KeyToolbarViewMode(
    modifier: Modifier = Modifier,
    onShareClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    HorizontalFloatingToolbar(
        modifier = modifier,
        expanded = true,
        colors = FloatingToolbarDefaults.vibrantFloatingToolbarColors()
    ) {
        IconButton(onClick = onShareClick) {
            Icon(
                imageVector = Icons.Rounded.Share,
                contentDescription = stringResource(R.string.key_toolbar_share)
            )
        }
        IconButton(onClick = onEditClick) {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = stringResource(R.string.key_toolbar_edit)
            )
        }
        IconButton(onClick = onDeleteClick) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = stringResource(R.string.key_toolbar_delete)
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun ViewModePreview() {
    GigaPassTheme {
        KeyToolbarViewMode(
            onShareClick = {},
            onEditClick = {},
            onDeleteClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun KeyToolbarEditMode(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onApplyClick: () -> Unit
) {
    HorizontalFloatingToolbar(
        modifier = modifier,
        expanded = true,
        colors = FloatingToolbarDefaults.vibrantFloatingToolbarColors()
    ) {
        IconButton(onClick = onCancelClick) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = stringResource(R.string.key_toolbar_cancel)
            )
        }
        IconButton(onClick = onApplyClick) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = stringResource(R.string.key_toolbar_apply)
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun EditModePreview() {
    GigaPassTheme {
        KeyToolbarEditMode(
            onCancelClick = {},
            onApplyClick = {}
        )
    }
}