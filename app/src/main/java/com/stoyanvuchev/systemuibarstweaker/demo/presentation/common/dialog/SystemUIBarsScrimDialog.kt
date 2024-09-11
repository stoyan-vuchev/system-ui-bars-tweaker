package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog

import android.os.Build
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.stoyanvuchev.systemuibarstweaker.ScrimStyle
import com.stoyanvuchev.systemuibarstweaker.SystemUIBarsConfiguration
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.composable.RadioButtonItem
import kotlin.math.roundToInt

/**
 * A dialog for tweaking the System UI Bars scrim.
 *
 * @param systemUIBarsConfiguration the currently applied configuration.
 * @param onDismissRequest the callback invoked when the dialog is dismissed.
 * @param onApplyRequest the callback containing the new configuration.
 **/
@Composable
fun SystemUIBarsScrimDialog(
    systemUIBarsConfiguration: SystemUIBarsConfiguration,
    onDismissRequest: () -> Unit,
    onApplyRequest: (SystemUIBarsConfiguration) -> Unit
) {

    var statusBarScrimStyle by remember {
        mutableStateOf(systemUIBarsConfiguration.statusBarStyle.scrimStyle)
    }

    var navigationBarScrimStyle by remember {
        mutableStateOf(systemUIBarsConfiguration.navigationBarStyle.scrimStyle)
    }

    var statusBarScrimLightThemeColorOpacity by remember(
        systemUIBarsConfiguration.statusBarStyle.scrimStyle
    ) {
        mutableFloatStateOf(
            if (systemUIBarsConfiguration.statusBarStyle.scrimStyle !is ScrimStyle.Custom) {
                ScrimStyle.Custom().lightThemeColorOpacity
            } else systemUIBarsConfiguration.statusBarStyle.scrimStyle.lightThemeColorOpacity
        )
    }

    var statusBarScrimDarkThemeColorOpacity by remember(
        systemUIBarsConfiguration.statusBarStyle.scrimStyle
    ) {
        mutableFloatStateOf(
            if (systemUIBarsConfiguration.statusBarStyle.scrimStyle !is ScrimStyle.Custom) {
                ScrimStyle.Custom().darkThemeColorOpacity
            } else systemUIBarsConfiguration.statusBarStyle.scrimStyle.darkThemeColorOpacity
        )
    }

    var navigationBarScrimLightThemeColorOpacity by remember(
        systemUIBarsConfiguration.navigationBarStyle.scrimStyle
    ) {
        mutableFloatStateOf(
            if (systemUIBarsConfiguration.navigationBarStyle.scrimStyle !is ScrimStyle.Custom) {
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> ScrimStyle.Custom().lightThemeColorOpacity
                    else -> ScrimStyle.Custom().darkThemeColorOpacity
                }
            } else systemUIBarsConfiguration.navigationBarStyle.scrimStyle.lightThemeColorOpacity
        )
    }

    var navigationBarScrimDarkThemeColorOpacity by remember(
        systemUIBarsConfiguration.navigationBarStyle.scrimStyle
    ) {
        mutableFloatStateOf(
            if (systemUIBarsConfiguration.navigationBarStyle.scrimStyle !is ScrimStyle.Custom) {
                ScrimStyle.Custom().darkThemeColorOpacity
            } else systemUIBarsConfiguration.navigationBarStyle.scrimStyle.darkThemeColorOpacity
        )
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
                text = stringResource(id = R.string.dialog_system_ui_bars_scrim_title),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDivider(
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

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    text = stringResource(id = R.string.dialog_system_ui_bars_scrim_status_bar_subtitle),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(16.dp))

                RadioButtonItem(
                    selected = statusBarScrimStyle is ScrimStyle.None,
                    label = stringResource(id = R.string.dialog_system_ui_bars_scrim_none_label),
                    description = stringResource(id = R.string.dialog_system_ui_bars_scrim_none_description),
                    onClick = { statusBarScrimStyle = ScrimStyle.None }
                )

                HorizontalDivider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    RadioButtonItem(
                        selected = statusBarScrimStyle is ScrimStyle.System,
                        label = stringResource(id = R.string.dialog_system_ui_bars_scrim_system_label),
                        description = stringResource(id = R.string.dialog_system_ui_bars_scrim_system_description),
                        onClick = { statusBarScrimStyle = ScrimStyle.System }
                    )
                    HorizontalDivider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))
                }

                RadioButtonItem(
                    selected = statusBarScrimStyle is ScrimStyle.Custom,
                    label = stringResource(id = R.string.dialog_system_ui_bars_scrim_custom_label),
                    description = stringResource(id = R.string.dialog_system_ui_bars_scrim_custom_description),
                    onClick = { statusBarScrimStyle = ScrimStyle.Custom() },
                    selectedContent = {

                        CustomScrimSelectedContent(
                            lightThemeColorOpacity = statusBarScrimLightThemeColorOpacity,
                            darkThemeColorOpacity = statusBarScrimDarkThemeColorOpacity,
                            onLightThemeColorOpacity = {
                                statusBarScrimLightThemeColorOpacity = it
                            },
                            onDarkThemeColorOpacity = {
                                statusBarScrimDarkThemeColorOpacity = it
                            }
                        )

                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                HorizontalDivider()

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    text = stringResource(id = R.string.dialog_system_ui_bars_scrim_navigation_bar_subtitle),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(16.dp))

                RadioButtonItem(
                    selected = navigationBarScrimStyle is ScrimStyle.None,
                    label = stringResource(id = R.string.dialog_system_ui_bars_scrim_none_label),
                    description = stringResource(id = R.string.dialog_system_ui_bars_scrim_none_description),
                    onClick = { navigationBarScrimStyle = ScrimStyle.None }
                )

                HorizontalDivider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    RadioButtonItem(
                        selected = navigationBarScrimStyle is ScrimStyle.System,
                        label = stringResource(id = R.string.dialog_system_ui_bars_scrim_system_label),
                        description = stringResource(id = R.string.dialog_system_ui_bars_scrim_system_description),
                        onClick = { navigationBarScrimStyle = ScrimStyle.System }
                    )
                    HorizontalDivider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))
                }

                RadioButtonItem(
                    selected = navigationBarScrimStyle is ScrimStyle.Custom,
                    label = stringResource(id = R.string.dialog_system_ui_bars_scrim_custom_label),
                    description = stringResource(id = R.string.dialog_system_ui_bars_scrim_custom_description),
                    onClick = { navigationBarScrimStyle = ScrimStyle.Custom() },
                    selectedContent = {

                        CustomScrimSelectedContent(
                            lightThemeColorOpacity = navigationBarScrimLightThemeColorOpacity,
                            darkThemeColorOpacity = navigationBarScrimDarkThemeColorOpacity,
                            onLightThemeColorOpacity = {
                                navigationBarScrimLightThemeColorOpacity = it
                            },
                            onDarkThemeColorOpacity = {
                                navigationBarScrimDarkThemeColorOpacity = it
                            }
                        )

                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                HorizontalDivider(
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
                                    statusBarStyle = systemUIBarsConfiguration.statusBarStyle
                                        .copy(
                                            scrimStyle = statusBarScrimStyle.copy(
                                                lightThemeColorOpacity = statusBarScrimLightThemeColorOpacity,
                                                darkThemeColorOpacity = statusBarScrimDarkThemeColorOpacity
                                            )
                                        ),
                                    navigationBarStyle = systemUIBarsConfiguration.navigationBarStyle
                                        .copy(
                                            scrimStyle = navigationBarScrimStyle.copy(
                                                lightThemeColorOpacity = navigationBarScrimLightThemeColorOpacity,
                                                darkThemeColorOpacity = navigationBarScrimDarkThemeColorOpacity,
                                                lightThemeColor = when {
                                                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> Color.White
                                                    else -> Color.Black
                                                }
                                            )
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

}

@Composable
fun CustomScrimSelectedContent(
    modifier: Modifier = Modifier,
    lightThemeColorOpacity: Float,
    darkThemeColorOpacity: Float,
    onLightThemeColorOpacity: (Float) -> Unit,
    onDarkThemeColorOpacity: (Float) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(24.dp),
                shape = MaterialTheme.shapes.large
            )
            .padding(24.dp)
            .then(modifier)
    ) {

        val lightThemeColorOpacityText by remember(lightThemeColorOpacity) {
            derivedStateOf {
                "Light Theme Color Opacity:   " +
                        "${(lightThemeColorOpacity * 100).roundToInt()}%"
            }
        }

        Text(
            text = lightThemeColorOpacityText,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Slider(
            valueRange = 0f..1f,
            value = lightThemeColorOpacity,
            onValueChange = onLightThemeColorOpacity
        )

        Spacer(modifier = Modifier.height(16.dp))

        val darkThemeColorOpacityText by remember(darkThemeColorOpacity) {
            derivedStateOf {
                "Dark Theme Color Opacity:   " +
                        "${(darkThemeColorOpacity * 100).roundToInt()}%"
            }
        }

        Text(
            text = darkThemeColorOpacityText,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Slider(
            valueRange = 0f..1f,
            value = darkThemeColorOpacity,
            onValueChange = onDarkThemeColorOpacity
        )

    }

}