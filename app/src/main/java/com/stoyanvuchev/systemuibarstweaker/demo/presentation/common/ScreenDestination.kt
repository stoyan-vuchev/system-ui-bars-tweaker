package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * An abstraction used to define the destination of a particular screen.
 *
 * @property route the route of the screen.
 * @property label an optional label of the screen.
 * @property icon an optional icon of the screen.
 **/
@Immutable
abstract class ScreenDestination(
    val route: String,
    @StringRes val label: Int? = null,
    val icon: ImageVector? = null
)