package com.github.xnovo3000.gigapass.feature.key.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.core.ui.credentialsFontFamily
import com.github.xnovo3000.gigapass.feature.key.R

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun KeyListItemUsername(
    modifier: Modifier = Modifier,
    isEditing: Boolean,
    state: TextFieldState
) {
    Row(
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
            .padding(start = 16.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BasicTextField(
            modifier = Modifier.weight(1F),
            state = state,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = credentialsFontFamily
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface)
        )
        if (!isEditing) {
            FilledTonalIconButton(
                modifier = Modifier
                    .minimumInteractiveComponentSize()
                    .size(
                        size = IconButtonDefaults.extraSmallContainerSize(
                            widthOption = IconButtonDefaults.IconButtonWidthOption.Wide
                        )
                    ),
                onClick = { /* TODO: Copy username */ }
            ) {
                Icon(
                    modifier = Modifier.size(IconButtonDefaults.extraSmallIconSize),
                    imageVector = Icons.Rounded.ContentCopy,
                    contentDescription = stringResource(R.string.key_item_password_copy)
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun UsernamePreview() {
    GigaPassTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceContainer) {
            KeyListItemUsername(
                isEditing = false,
                state = rememberTextFieldState("Test")
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun UsernameEditModePreview() {
    GigaPassTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceContainer) {
            KeyListItemUsername(
                isEditing = true,
                state = rememberTextFieldState("Test")
            )
        }
    }
}