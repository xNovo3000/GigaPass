package com.github.xnovo3000.gigapass.feature.home.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.xnovo3000.gigapass.feature.home.model.PasswordEntry

sealed interface HomeScreenSearchState {
    data object Closed : HomeScreenSearchState
    data class Open(val searchString: String) : HomeScreenSearchState
}

data class HomeScreenState(
    val entries: List<PasswordEntry>,
    val search: HomeScreenSearchState
)

sealed interface HomeScreenClick {
    data object Search : HomeScreenClick
    data object New : HomeScreenClick
    data class Element(val id: Int) : HomeScreenClick
}

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onClick: (HomeScreenClick) -> Unit
) {

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {

}