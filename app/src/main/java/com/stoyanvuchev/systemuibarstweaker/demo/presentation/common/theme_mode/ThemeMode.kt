package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.stoyanvuchev.systemuibarstweaker.demo.R

/**
 * Constants for working with a theme mode.
 *
 * @param description the resource ID of the theme mode description.
 **/
enum class ThemeMode(
    @StringRes val description: Int
) {

    /**
     * An automatic theme mode, following the System theme.
     **/
    @RequiresApi(Build.VERSION_CODES.P)
    System(description = R.string.theme_mode_system_description),

    /**
     * Used for a light theme mode.
     **/
    Light(description = R.string.theme_mode_light_description),

    /**
     * Used for a dark theme mode.
     **/
    Dark(description = R.string.theme_mode_dark_description);

    companion object {

        /**
         * Returns [ThemeMode.System] if API >= 28, otherwise [ThemeMode.Light].
         **/
        val Default get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) System else Light

    }

}

/**
 * A CompositionLocal key for a [ThemeMode].
 **/
val LocalThemeMode = compositionLocalOf { ThemeMode.Default }

/**
 * Checks if the currently applied [ThemeMode] is [ThemeMode.Dark].
 **/
@Composable
fun isSystemInDarkThemeMode() = when (LocalThemeMode.current) {
    ThemeMode.Light -> false
    ThemeMode.Dark -> true
    else -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) isSystemInDarkTheme() else false
}