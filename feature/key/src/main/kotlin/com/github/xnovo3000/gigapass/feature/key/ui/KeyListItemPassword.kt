package com.github.xnovo3000.gigapass.feature.key.ui

import android.content.ClipData
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.toClipEntry
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.core.ui.credentialsFontFamily
import com.github.xnovo3000.gigapass.feature.key.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun KeyListItemPassword(
    modifier: Modifier = Modifier,
    isEditing: Boolean,
    state: TextFieldState
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
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
        // TODO: Add biometric prompt request
        var showPasswordRequested by remember { mutableStateOf(false) }
        BasicSecureTextField(
            modifier = Modifier.weight(1F),
            enabled = isEditing,
            state = state,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = credentialsFontFamily
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            textObfuscationMode = if (isEditing || showPasswordRequested) {
                TextObfuscationMode.Visible
            } else {
                TextObfuscationMode.Hidden
            }
        )
        if (!isEditing) {
            val iconButtonModifier = Modifier
                .minimumInteractiveComponentSize()
                .size(
                    size = IconButtonDefaults.extraSmallContainerSize(
                        widthOption = IconButtonDefaults.IconButtonWidthOption.Wide
                    )
                )
            if (showPasswordRequested) {
                val coroutineScope = rememberCoroutineScope()
                val clipboard = LocalClipboard.current
                FilledTonalIconButton(
                    modifier = iconButtonModifier,
                    onClick = {
                        coroutineScope.launch {
                            val clipData = ClipData.newPlainText("password", state.text)
                            clipboard.setClipEntry(clipData.toClipEntry())
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(IconButtonDefaults.extraSmallIconSize),
                        imageVector = Icons.Rounded.ContentCopy,
                        contentDescription = stringResource(R.string.key_item_password_copy)
                    )
                }
            } else {
                FilledTonalIconButton(
                    modifier = iconButtonModifier,
                    onClick = { showPasswordRequested = true }
                ) {
                    Icon(
                        modifier = Modifier.size(IconButtonDefaults.extraSmallIconSize),
                        imageVector = Icons.Rounded.Visibility,
                        contentDescription = stringResource(R.string.key_item_password_show)
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PasswordPreview() {
    GigaPassTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceContainer) {
            KeyListItemPassword(
                isEditing = false,
                state = rememberTextFieldState("Test")
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PasswordEditModePreview() {
    GigaPassTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceContainer) {
            KeyListItemPassword(
                isEditing = true,
                state = rememberTextFieldState("Test")
            )
        }
    }
}