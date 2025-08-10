package com.github.xnovo3000.gigapass.feature.key.ui

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.github.xnovo3000.gigapass.core.ui.GigaPassTheme
import com.github.xnovo3000.gigapass.feature.key.R

@Composable
fun KeyListHeadlineWebsiteApplication(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.key_headline_website_application),
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WebsiteApplicationPreview() {
    GigaPassTheme {
        Surface { KeyListHeadlineWebsiteApplication() }
    }
}

@Composable
fun KeyListHeadlineCredentials(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.key_headline_credentials),
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CredentialsPreview() {
    GigaPassTheme {
        Surface { KeyListHeadlineCredentials() }
    }
}

@Composable
fun KeyListHeadlineNotes(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.key_headline_notes),
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NotesPreview() {
    GigaPassTheme {
        Surface { KeyListHeadlineNotes() }
    }
}