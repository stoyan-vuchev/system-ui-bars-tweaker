package com.stoyanvuchev.systemuibarstweaker.demo.data.preferences

import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowInsetsControllerCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stoyanvuchev.systemuibarstweaker.SystemBarStyle
import com.stoyanvuchev.systemuibarstweaker.SystemUIBarsConfiguration
import com.stoyanvuchev.systemuibarstweaker.demo.domain.preferences.AppPreferences
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.color_scheme.ColorScheme
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode.ThemeMode
import kotlinx.coroutines.flow.first

class AppPreferencesImpl(
    private val dataStore: DataStore<Preferences>
) : AppPreferences {

    companion object {

        const val APP_DATA_STORE_PREFERENCES = "app_preferences"

        private val THEME_MODE = stringPreferencesKey("theme_mode")
        private val COLOR_SCHEME = stringPreferencesKey("color_scheme")
        private val SYSTEM_UI_BARS_CONFIG = stringPreferencesKey("system_ui_bars_config")

        private val systemBarStyle get() = SystemBarStyle(color = Color.Unspecified)
        val SystemUIBarsConfiguration.Companion.DEFAULT
            get() = SystemUIBarsConfiguration(
                isStatusBarVisible = true,
                isNavigationBarVisible = true,
                enableEdgeToEdge = true,
                statusBarStyle = systemBarStyle.copy(enforceContrast = false),
                navigationBarStyle = systemBarStyle.copy(enforceContrast = Build.VERSION.SDK_INT < Build.VERSION_CODES.O),
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            )

    }

    override suspend fun getThemeMode(): ThemeMode {
        return dataStore.data.first()[THEME_MODE]?.let { ThemeMode.valueOf(it) }
            ?: ThemeMode.Default
    }

    override suspend fun setThemeMode(themeMode: ThemeMode) {
        dataStore.edit { it[THEME_MODE] = themeMode.name }
    }

    override suspend fun getColorScheme(): ColorScheme {
        return dataStore.data.first()[COLOR_SCHEME]?.let { ColorScheme.valueOf(it) }
            ?: ColorScheme.System
    }

    override suspend fun setColorScheme(colorScheme: ColorScheme) {
        dataStore.edit { it[COLOR_SCHEME] = colorScheme.name }
    }

    override suspend fun getSystemUIBarsConfiguration(): SystemUIBarsConfiguration {
        return dataStore.data.first()[SYSTEM_UI_BARS_CONFIG]?.let { json ->
            val typeToken = object : TypeToken<SystemUIBarsConfiguration>() {}.type
            Gson().fromJson(json, typeToken)
        } ?: SystemUIBarsConfiguration.DEFAULT
    }

    override suspend fun setSystemUIBarsConfiguration(configuration: SystemUIBarsConfiguration) {
        val typeToken = object : TypeToken<SystemUIBarsConfiguration>() {}.type
        dataStore.edit { it[SYSTEM_UI_BARS_CONFIG] = Gson().toJson(configuration, typeToken) }
    }

}