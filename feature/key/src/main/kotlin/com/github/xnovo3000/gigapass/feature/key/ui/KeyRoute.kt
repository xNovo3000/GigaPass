package com.github.xnovo3000.gigapass.feature.key.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.github.xnovo3000.gigapass.feature.key.viewmodel.KeyViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyRoute(
    viewModel: KeyViewModel,
    onBackClick: () -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var isDeleting by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            KeyTopBar(onBackClick = onBackClick)
        },
        floatingActionButton = {
            KeyToolbar(
                isEditing = isEditing,
                onShareClick = { /* TODO: Share as text */ },
                onEditClick = { isEditing = true },
                onDeleteClick = { isDeleting = true },
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
            innerPadding = withToolbarPadding(innerPadding),
            isEditing = isEditing,
            serviceNameTextFieldState = viewModel.serviceNameTextFieldState,
            usernameTextFieldState = viewModel.usernameTextFieldState,
            passwordTextFieldState = viewModel.passwordTextFieldState
        )
    }
    if (isDeleting) {
        val coroutineScope = rememberCoroutineScope()
        KeyDeleteDialog(
            onDismissRequest = {
                isDeleting = false
            },
            onConfirmRequest = {
                isDeleting = false
                coroutineScope.launch {
                    viewModel.delete()
                    onBackClick()
                }
            }
        )
    }
}