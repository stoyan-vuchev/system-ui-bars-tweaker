package com.stoyanvuchev.systemuibarstweaker.demo.domain.preferences

import com.stoyanvuchev.systemuibarstweaker.SystemUIBarsConfiguration
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.color_scheme.ColorScheme
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode.ThemeMode

/**
 * An abstraction with methods for getting and setting app preferences.
 **/
interface AppPreferences {

    suspend fun getThemeMode(): ThemeMode
    suspend fun setThemeMode(themeMode: ThemeMode)

    suspend fun getColorScheme(): ColorScheme
    suspend fun setColorScheme(colorScheme: ColorScheme)

    suspend fun getSystemUIBarsConfiguration(): SystemUIBarsConfiguration
    suspend fun setSystemUIBarsConfiguration(configuration: SystemUIBarsConfiguration)

}