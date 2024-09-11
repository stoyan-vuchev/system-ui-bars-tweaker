package com.stoyanvuchev.systemuibarstweaker.demo.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.stoyanvuchev.systemuibarstweaker.SystemUIBarsConfiguration
import com.stoyanvuchev.systemuibarstweaker.demo.domain.preferences.AppPreferences
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.color_scheme.ColorScheme
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode.ThemeMode
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AppPreferencesImpl(
    private val dataStore: DataStore<Preferences>
) : AppPreferences {

    companion object {

        const val APP_DATA_STORE_PREFERENCES = "app_preferences"

        private val THEME_MODE = stringPreferencesKey("theme_mode")
        private val COLOR_SCHEME = stringPreferencesKey("color_scheme")
        private val SYSTEM_UI_BARS_CONFIG = stringPreferencesKey("system_ui_bars_config")

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
            Json.decodeFromString(json)
        } ?: SystemUIBarsConfiguration.DEFAULT
    }

    override suspend fun setSystemUIBarsConfiguration(configuration: SystemUIBarsConfiguration) {
        dataStore.edit { it[SYSTEM_UI_BARS_CONFIG] = Json.encodeToString(configuration) }
    }

}