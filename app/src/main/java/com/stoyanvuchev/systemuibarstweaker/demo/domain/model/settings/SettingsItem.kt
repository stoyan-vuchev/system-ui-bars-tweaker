package com.stoyanvuchev.systemuibarstweaker.demo.domain.model.settings

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog

/**
 * A data class containing the necessary properties for a [SettingsCategory] item.
 *
 * @property label the resource ID of the item label.
 * @property description the resource ID of the the item description.
 * @property icon the icon of the item.
 * @property action the action of the item to be invoked on click.
 **/
@Stable
data class SettingsItem(
    @StringRes val label: Int,
    @StringRes val description: Int,
    val icon: ImageVector,
    val action: Pair<String?, InvokedDialog?>
)