package com.stoyanvuchev.systemuibarstweaker.demo.presentation.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Immutable
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ScreenDestination

/**
 * Contains the screen destinations of all related settings.
 **/
@Immutable
object SettingsScreenDestinations {

    /**
     * The screen destination of the [SettingsScreen].
     **/
    data object Settings : ScreenDestination(
        route = "settings_screen_route",
        label = R.string.settings_screen_label,
        icon = Icons.Rounded.Settings
    )

    /**
     * The unique route of the [settingsNavigationGraph].
     **/
    val navigationGraphRoute: String get() = "settings_navigation_graph"

}