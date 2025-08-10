package com.github.xnovo3000.gigapass.feature.keychain.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity

@Composable
fun StatusBarProtection(
    modifier: Modifier = Modifier
) {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val calculatedHeight = WindowInsets.statusBars.getTop(LocalDensity.current).toFloat()
    Canvas(modifier = modifier.fillMaxWidth()) {
        drawRect(
            color = surfaceColor,
            size = Size(size.width, calculatedHeight),
            alpha = 0.75F,
        )
    }
}