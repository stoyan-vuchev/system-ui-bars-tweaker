package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.stoyanvuchev.systemuibarstweaker.SystemUIBarsConfiguration
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.data.preferences.AppPreferencesImpl.Companion.DEFAULT
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.composable.ClickableSwitchItem

/**
 * A dialog for tweaking the System UI Bars.
 *
 * @param systemUIBarsConfiguration the currently applied configuration.
 * @param onDismissRequest the callback invoked when the dialog is dismissed.
 * @param onApplyRequest the callback containing the new configuration.
 **/
@Composable
fun SystemUIBarsTweaksDialog(
    systemUIBarsConfiguration: SystemUIBarsConfiguration,
    onDismissRequest: () -> Unit,
    onApplyRequest: (SystemUIBarsConfiguration) -> Unit
) {

    var transparentStatusBar by remember {
        mutableStateOf(systemUIBarsConfiguration.statusBarStyle.color.isUnspecified)
    }

    var transparentNavigationBar by remember {
        mutableStateOf(systemUIBarsConfiguration.navigationBarStyle.color.isUnspecified)
    }

    var statusBarContrast by remember {
        mutableStateOf(systemUIBarsConfiguration.statusBarStyle.enforceContrast)
    }

    var navigationBarContrast by remember {
        mutableStateOf(systemUIBarsConfiguration.navigationBarStyle.enforceContrast)
    }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.extraLarge)
                .background(
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp)
                )
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                text = stringResource(id = R.string.dialog_system_ui_bars_tweaks_title),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            Divider(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .clip(RoundedCornerShape(50))
            )

            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(6.dp))

                ClickableSwitchItem(
                    label = stringResource(id = R.string.dialog_system_ui_bars_tweaks_translucent_status_bar_item_label),
                    description = stringResource(id = R.string.dialog_system_ui_bars_tweaks_translucent_status_bar_item_description),
                    checked = transparentStatusBar,
                    onCheckedChange = {
                        transparentStatusBar = !transparentStatusBar
                    }
                )

                Divider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))

                ClickableSwitchItem(
                    label = stringResource(id = R.string.dialog_system_ui_bars_tweaks_translucent_nav_bar_item_label),
                    description = stringResource(id = R.string.dialog_system_ui_bars_tweaks_translucent_nav_bar_item_description),
                    checked = transparentNavigationBar,
                    onCheckedChange = {
                        transparentNavigationBar = !transparentNavigationBar
                    }
                )

                Divider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))

                ClickableSwitchItem(
                    label = stringResource(id = R.string.dialog_system_ui_bars_tweaks_status_bar_contrast_item_label),
                    description = stringResource(id = R.string.dialog_system_ui_bars_tweaks_status_bar_contrast_item_description),
                    checked = statusBarContrast,
                    onCheckedChange = {
                        statusBarContrast = !statusBarContrast
                    }
                )

                Divider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))

                ClickableSwitchItem(
                    label = stringResource(id = R.string.dialog_system_ui_bars_tweaks_nav_bar_contrast_item_label),
                    description = stringResource(id = R.string.dialog_system_ui_bars_tweaks_nav_bar_contrast_item_description),
                    checked = navigationBarContrast,
                    onCheckedChange = {
                        navigationBarContrast = !navigationBarContrast
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

            }

            Divider(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .clip(RoundedCornerShape(50))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 20.dp,
                    alignment = Alignment.End
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedButton(
                    onClick = onDismissRequest,
                    content = { Text(text = stringResource(id = R.string.dialog_action_cancel)) }
                )

                Button(
                    onClick = {
                        onApplyRequest(
                            systemUIBarsConfiguration.copy(
                                statusBarStyle = systemUIBarsConfiguration.statusBarStyle.copy(
                                    color = if (!transparentStatusBar) Color.DarkGray
                                    else SystemUIBarsConfiguration.DEFAULT.statusBarStyle.color,
                                    enforceContrast = statusBarContrast
                                ),
                                navigationBarStyle = systemUIBarsConfiguration.navigationBarStyle.copy(
                                    color = if (!transparentNavigationBar) Color.DarkGray
                                    else SystemUIBarsConfiguration.DEFAULT.navigationBarStyle.color,
                                    enforceContrast = navigationBarContrast
                                )
                            )
                        )
                        onDismissRequest()
                    },
                    content = { Text(text = stringResource(id = R.string.dialog_action_apply)) }
                )

            }

        }

    }

}