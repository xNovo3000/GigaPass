package com.github.xnovo3000.gigapass.core.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

val GigaPassTransitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
    ContentTransform(
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            initialOffset = { it / 8 }
        ) + fadeIn(
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
        ),
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            targetOffset = { it / 8 }
        ) + fadeOut(
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
        ),
    )
}

val GigaPassPopTransitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
    ContentTransform(
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            initialOffset = { it / 8 }
        ) + fadeIn(
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
        ),
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            targetOffset = { it / 8 }
        ) + fadeOut(
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
        )
    )
}

val GigaPassPredictivePopTransitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
    ContentTransform(
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            initialOffset = { it / 8 }
        ) + fadeIn(
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
        ),
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            targetOffset = { it / 8 }
        ) + fadeOut(
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
        )
    )
}