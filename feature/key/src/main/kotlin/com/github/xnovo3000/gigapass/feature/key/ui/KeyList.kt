package com.github.xnovo3000.gigapass.feature.key.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme

@Composable
fun KeyList(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    isEditing: Boolean,
    serviceNameTextFieldState: TextFieldState,
    usernameTextFieldState: TextFieldState,
    passwordTextFieldState: TextFieldState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceContainer)
            .verticalScroll(state = rememberScrollState())
    ) {
        Spacer(modifier = Modifier.padding(top = innerPadding.calculateTopPadding()))
        KeyListHeadlineWebsiteApplication(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 8.dp)
        )
        KeyListItemService(
            modifier = Modifier.padding(horizontal = 16.dp),
            isEditing = isEditing,
            state = serviceNameTextFieldState
        )
        KeyListHeadlineCredentials(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 8.dp)
        )
        KeyListItemUsername(
            modifier = Modifier.padding(horizontal = 16.dp),
            isEditing = isEditing,
            state = usernameTextFieldState
        )
        Spacer(modifier = Modifier.height(2.dp))
        KeyListItemPassword(
            modifier = Modifier.padding(horizontal = 16.dp),
            isEditing = isEditing,
            state = passwordTextFieldState
        )
        Spacer(modifier = Modifier.padding(top = innerPadding.calculateBottomPadding()))
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    GigaPassTheme {
        Surface {
            KeyList(
                isEditing = false,
                serviceNameTextFieldState = rememberTextFieldState("Amazon"),
                usernameTextFieldState = rememberTextFieldState("Test"),
                passwordTextFieldState = rememberTextFieldState("1234")
            )
        }
    }
}