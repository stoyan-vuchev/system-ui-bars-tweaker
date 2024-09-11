package com.stoyanvuchev.systemuibarstweaker.demo.domain.model.settings

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BuildCircle
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.color_scheme.LocalColorScheme
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode.LocalThemeMode
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode.isSystemInDarkThemeMode

/**
 * A data class containing a list of [SettingsItem]s that belong to a certain category.
 *
 * @property label the label of the category.
 * @property items the items belonging to the category.
 **/
@Stable
data class SettingsCategory(
    @StringRes val label: Int,
    val items: List<SettingsItem>
) {

    companion object {

        /**
         * Returns a list of categories for the [SettingsScreen].
         **/
        @Composable
        fun categoryList() = listOf(
            SettingsCategory(
                label = R.string.settings_category_general_label,
                items = listOf(
                    SettingsItem(
                        label = R.string.settings_category_general_theme_item_label,
                        description = LocalThemeMode.current.description,
                        icon = if (isSystemInDarkThemeMode()) Icons.Rounded.DarkMode
                        else Icons.Rounded.LightMode,
                        action = Pair(null, InvokedDialog.InvokedThemeDialog)
                    ),
                    SettingsItem(
                        label = R.string.settings_category_general_color_scheme_item_label,
                        description = LocalColorScheme.current.description,
                        icon = Icons.Rounded.Palette,
                        action = Pair(null, InvokedDialog.InvokedColorSchemeDialog)
                    ),
                    SettingsItem(
                        label = R.string.settings_category_general_system_ui_bars_item_label,
                        description = R.string.settings_category_general_system_ui_bars_item_description,
                        icon = Icons.Rounded.BuildCircle,
                        action = Pair(null, InvokedDialog.InvokedSystemUIBarsTweaksDialog)
                    )
                )
            ),
            SettingsCategory(
                label = R.string.settings_category_other_label,
                items = listOf(
                    SettingsItem(
                        label = R.string.settings_category_other_about_item_label,
                        description = R.string.settings_category_other_about_item_description,
                        icon = Icons.Rounded.Info,
                        action = Pair("test_gesture", null) // fixme
                    )
                )
            )
        )

    }

}