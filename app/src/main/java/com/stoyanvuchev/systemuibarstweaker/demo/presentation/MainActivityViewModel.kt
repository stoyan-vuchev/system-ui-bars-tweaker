package com.stoyanvuchev.systemuibarstweaker.demo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoyanvuchev.systemuibarstweaker.SystemUIBarsConfiguration
import com.stoyanvuchev.systemuibarstweaker.demo.data.preferences.AppPreferencesImpl.Companion.DEFAULT
import com.stoyanvuchev.systemuibarstweaker.demo.domain.preferences.AppPreferences
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.color_scheme.ColorScheme
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode.ThemeMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _themeMode = MutableStateFlow(ThemeMode.Default)
    val themeMode = _themeMode.asStateFlow()

    private val _colorScheme = MutableStateFlow(ColorScheme.System)
    val colorScheme = _colorScheme.asStateFlow()

    private val _invokedDialog = MutableStateFlow<InvokedDialog>(InvokedDialog.None)
    val invokedDialog = _invokedDialog.asStateFlow()

    private val _systemUIBarsConfiguration = MutableStateFlow(SystemUIBarsConfiguration.DEFAULT)
    val systemUIBarsConfiguration = _systemUIBarsConfiguration.asStateFlow()

    init {
        getThemeMode()
        getColorScheme()
        getSystemUIBarsConfiguration()
    }

    fun onDialogInvocation(invocation: InvokedDialog) {
        _invokedDialog.update { invocation }
    }

    fun onApplyThemeMode(newThemeMode: ThemeMode) {
        _themeMode.update { newThemeMode }
        setThemeMode(newThemeMode)
    }

    fun onApplyColorScheme(newColorScheme: ColorScheme) {
        _colorScheme.update { newColorScheme }
        setColorScheme(newColorScheme)
    }

    fun onApplyNewSystemUIBarsConfiguration(newConfiguration: SystemUIBarsConfiguration) {
        _systemUIBarsConfiguration.update { newConfiguration }
        setSystemUIBarsConfiguration(newConfiguration)
    }

    private fun getThemeMode() {
        viewModelScope.launch {
            val themeMode = withContext(Dispatchers.IO) { appPreferences.getThemeMode() }
            _themeMode.update { themeMode }
        }
    }

    private fun getColorScheme() {
        viewModelScope.launch {
            val colorScheme = withContext(Dispatchers.IO) { appPreferences.getColorScheme() }
            _colorScheme.update { colorScheme }
        }
    }

    private fun getSystemUIBarsConfiguration() {
        viewModelScope.launch {

            val configuration = withContext(Dispatchers.IO) {
                appPreferences.getSystemUIBarsConfiguration()
            }

            _systemUIBarsConfiguration.update { configuration }

        }
    }

    private fun setThemeMode(newThemeMode: ThemeMode) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appPreferences.setThemeMode(newThemeMode)
            }
        }
    }

    private fun setColorScheme(newColorScheme: ColorScheme) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appPreferences.setColorScheme(newColorScheme)
            }
        }
    }

    private fun setSystemUIBarsConfiguration(newConfiguration: SystemUIBarsConfiguration) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appPreferences.setSystemUIBarsConfiguration(newConfiguration)
            }
        }
    }

}