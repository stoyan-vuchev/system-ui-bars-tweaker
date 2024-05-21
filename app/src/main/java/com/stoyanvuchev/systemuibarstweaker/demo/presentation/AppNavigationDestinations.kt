package com.stoyanvuchev.systemuibarstweaker.demo.presentation

import androidx.compose.runtime.Immutable
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.docs.DocsScreenDestinations
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.ExamplesScreenDestinations
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.settings.SettingsScreenDestinations
import kotlinx.collections.immutable.persistentListOf

/**
 * Contains parameters related to the navigation of the app.
 **/
@Immutable
object AppNavigationDestinations {

    /**
     * Returns a list of the main navigation destinations of the app
     * and using them for the [AppBottomNavigationBar] and [AppNavigationRail].
     **/
    val navigationDestinations
        get() = persistentListOf(
            DocsScreenDestinations.Docs,
            ExamplesScreenDestinations.Examples,
            SettingsScreenDestinations.Settings
        )

}