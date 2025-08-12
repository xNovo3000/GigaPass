package com.github.xnovo3000.gigapass.feature.key.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.github.xnovo3000.gigapass.feature.key.viewmodel.KeyViewModel

@Composable
fun KeyRoute(viewModel: KeyViewModel) {
    Scaffold { innerPadding ->
        KeyList(
            innerPadding = innerPadding,
            isEditing = false,
            serviceNameTextFieldState = viewModel.serviceNameTextFieldState,
            usernameTextFieldState = viewModel.usernameTextFieldState,
            passwordTextFieldState = viewModel.passwordTextFieldState
        )
    }
}