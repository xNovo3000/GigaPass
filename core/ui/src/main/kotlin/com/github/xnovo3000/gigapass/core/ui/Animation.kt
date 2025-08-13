package com.github.xnovo3000.gigapass.core.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

val GigaPassTransitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
    ContentTransform(
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(),
            initialOffset = { it / 8 }
        ) + fadeIn(
            animationSpec = tween()
        ),
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(),
            targetOffset = { it / 8 }
        ) + fadeOut(
            animationSpec = tween()
        ),
    )
}

val GigaPassPopTransitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
    ContentTransform(
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(),
            initialOffset = { it / 8 }
        ) + fadeIn(
            animationSpec = tween()
        ),
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(),
            targetOffset = { it / 8 }
        ) + fadeOut(
            animationSpec = tween()
        )
    )
}