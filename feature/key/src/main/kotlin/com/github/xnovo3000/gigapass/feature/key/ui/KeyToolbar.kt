package com.github.xnovo3000.gigapass.feature.key.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.key.R

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun KeyToolbar(
    modifier: Modifier = Modifier,
    isEditing: Boolean,
    onShareClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onCancelClick: () -> Unit,
    onApplyClick: () -> Unit
) {
    HorizontalFloatingToolbar(
        modifier = modifier,
        expanded = true,
        colors = FloatingToolbarDefaults.vibrantFloatingToolbarColors(),
        expandedShadowElevation = 6.dp
    ) {
        if (isEditing) {
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
        } else {
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
}

@Composable
fun withToolbarPadding(
    innerPadding: PaddingValues
): PaddingValues = PaddingValues(
    top = innerPadding.calculateTopPadding(),
    bottom = innerPadding.calculateBottomPadding() + 128.dp,
    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ViewModePreview() {
    GigaPassTheme {
        Surface {
            KeyToolbar(
                isEditing = false,
                onShareClick = {},
                onEditClick = {},
                onDeleteClick = {},
                onCancelClick = {},
                onApplyClick = {}
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EditModePreview() {
    GigaPassTheme {
        Surface {
            KeyToolbar(
                isEditing = true,
                onShareClick = {},
                onEditClick = {},
                onDeleteClick = {},
                onCancelClick = {},
                onApplyClick = {}
            )
        }
    }
}