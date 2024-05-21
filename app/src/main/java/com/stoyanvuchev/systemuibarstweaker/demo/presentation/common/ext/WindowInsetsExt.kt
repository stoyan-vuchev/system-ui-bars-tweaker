package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ext

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection

/**
 * Returns empty [WindowInsets].
 **/
val WindowInsets.Companion.Empty: WindowInsets
    get() = WindowInsets(0, 0, 0, 0)

@Composable
fun animateWindowInsets(
    targetValue: WindowInsets,
    animationSpec: AnimationSpec<Int> = spring(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessLow,
        visibilityThreshold = Int.VisibilityThreshold
    ),
    label: String = "WindowInsetsAnimation"
): State<WindowInsets> {

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val left by animateIntAsState(
        targetValue = targetValue.getLeft(density, layoutDirection),
        animationSpec = animationSpec,
        label = label
    )

    val top by animateIntAsState(
        targetValue = targetValue.getTop(density),
        animationSpec = animationSpec,
        label = label
    )

    val right by animateIntAsState(
        targetValue = targetValue.getRight(density, layoutDirection),
        animationSpec = animationSpec,
        label = label
    )

    val bottom by animateIntAsState(
        targetValue = targetValue.getBottom(density),
        animationSpec = animationSpec,
        label = label
    )

    return rememberUpdatedState(
        WindowInsets(
            left = left,
            top = top,
            right = right,
            bottom = bottom
        )
    )

}