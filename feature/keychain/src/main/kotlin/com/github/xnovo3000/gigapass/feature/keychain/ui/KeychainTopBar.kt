package com.github.xnovo3000.gigapass.feature.keychain.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExpandedFullScreenSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarState
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopSearchBar
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.keychain.R
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun KeychainTopBarInputField(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    searchBarState: SearchBarState
) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchText = stringResource(R.string.keychain_search_hint)
    SearchBarDefaults.InputField(
        modifier = modifier,
        textFieldState = textFieldState,
        searchBarState = searchBarState,
        onSearch = { keyboardController?.hide() },
        leadingIcon = {
            if (searchBarState.currentValue == SearchBarValue.Expanded) {
                IconButton(
                    onClick = {
                        textFieldState.setTextAndPlaceCursorAtEnd("")
                        coroutineScope.launch { searchBarState.animateToCollapsed() }
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = stringResource(R.string.keychain_search_clear)
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = searchText
                )
            }
        },
        trailingIcon = {
            if (searchBarState.currentValue == SearchBarValue.Expanded) {
                IconButton(
                    onClick = {
                        textFieldState.setTextAndPlaceCursorAtEnd("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = stringResource(R.string.keychain_search_clear)
                    )
                }
            }
        },
        placeholder = {
            Text(text = searchText)
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@ExperimentalMaterial3Api
@Composable
private fun InputFieldPreview() {
    GigaPassTheme {
        KeychainTopBarInputField(
            textFieldState = rememberTextFieldState(),
            searchBarState = rememberSearchBarState()
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun KeychainTopBarSearchBar(
    modifier: Modifier = Modifier,
    searchBarState: SearchBarState,
    inputField: @Composable () -> Unit
) {
    TopSearchBar(
        modifier = modifier,
        state = searchBarState,
        inputField = inputField
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@ExperimentalMaterial3Api
@Composable
private fun SearchBarPreview() {
    GigaPassTheme {
        val searchBarState = rememberSearchBarState()
        KeychainTopBarSearchBar(
            searchBarState = searchBarState,
            inputField = {
                KeychainTopBarInputField(
                    textFieldState = rememberTextFieldState(),
                    searchBarState = searchBarState
                )
            }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun KeychainTopBarSearchView(
    modifier: Modifier = Modifier,
    searchBarState: SearchBarState,
    inputField: @Composable () -> Unit
) {
    ExpandedFullScreenSearchBar(
        modifier = modifier,
        state = searchBarState,
        inputField = inputField
    ) {

    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@ExperimentalMaterial3Api
@Composable
private fun SearchViewPreview() {
    GigaPassTheme {
        val searchBarState = rememberSearchBarState(SearchBarValue.Expanded)
        val inputField = @Composable {
            KeychainTopBarInputField(
                textFieldState = rememberTextFieldState(),
                searchBarState = searchBarState
            )
        }
        Box {
            KeychainTopBarSearchBar(
                searchBarState = searchBarState,
                inputField = inputField
            )
            KeychainTopBarSearchView(
                searchBarState = searchBarState,
                inputField = inputField
            )
        }
    }
}