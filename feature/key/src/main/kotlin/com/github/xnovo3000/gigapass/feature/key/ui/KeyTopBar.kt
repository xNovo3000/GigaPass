package com.github.xnovo3000.gigapass.feature.key.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.key.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun KeyTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            FilledTonalIconButton(
                modifier = Modifier.minimumInteractiveComponentSize()
                    .size(
                        size = IconButtonDefaults.smallContainerSize(
                            widthOption = IconButtonDefaults.IconButtonWidthOption.Narrow
                        )
                    ),
                onClick = onBackClick,
            ) {
                Icon(
                    modifier = Modifier.size(IconButtonDefaults.smallIconSize),
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.key_top_back)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ViewModePreview() {
    GigaPassTheme {
        KeyTopBar(
            onBackClick = {}
        )
    }
}