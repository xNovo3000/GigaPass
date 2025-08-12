package com.github.xnovo3000.gigapass.feature.key.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.github.xnovo3000.gigapass.feature.key.viewmodel.KeyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyRoute(
    viewModel: KeyViewModel,
    onBackClick: () -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            KeyTopBar(onBackClick = onBackClick)
        },
        floatingActionButton = {
            KeyToolbar(
                isEditing = isEditing,
                onShareClick = { /* TODO: Share as text */ },
                onEditClick = { isEditing = true },
                onDeleteClick = { /* TODO: Delete current key */ },
                onCancelClick = {
                    isEditing = false
                    viewModel.loadDefaults()
                },
                onApplyClick = {
                    isEditing = false
                    viewModel.apply()
                },
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        KeyList(
            innerPadding = innerPadding,
            isEditing = isEditing,
            serviceNameTextFieldState = viewModel.serviceNameTextFieldState,
            usernameTextFieldState = viewModel.usernameTextFieldState,
            passwordTextFieldState = viewModel.passwordTextFieldState
        )
    }
}