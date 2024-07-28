package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.composable.RadioButtonItem

/**
 * A dialog for tweaking the [ThemeMode] of the app.
 *
 * @param themeMode the currently applied theme mode.
 * @param onDismissRequest the callback invoked when the dialog is dismissed.
 * @param onApplyRequest the callback with the theme mode to be applied.
 **/
@Composable
fun ThemeModeDialog(
    themeMode: ThemeMode,
    onDismissRequest: () -> Unit,
    onApplyRequest: (ThemeMode) -> Unit
) {

    var newThemeMode by remember { mutableStateOf(themeMode) }

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
                text = stringResource(id = R.string.dialog_theme_mode_title),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .clip(RoundedCornerShape(50))
            )

            Spacer(modifier = Modifier.height(6.dp))

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                RadioButtonItem(
                    selected = newThemeMode == ThemeMode.System,
                    label = stringResource(id = R.string.dialog_theme_mode_system_label),
                    description = stringResource(id = R.string.dialog_theme_mode_system_description),
                    onClick = { newThemeMode = ThemeMode.System }
                )

                HorizontalDivider(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                )

            }

            RadioButtonItem(
                selected = newThemeMode == ThemeMode.Light,
                label = stringResource(id = R.string.dialog_theme_mode_light_label),
                description = stringResource(id = R.string.dialog_theme_mode_light_description),
                onClick = { newThemeMode = ThemeMode.Light }
            )

            HorizontalDivider(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp)
            )

            RadioButtonItem(
                selected = newThemeMode == ThemeMode.Dark,
                label = stringResource(id = R.string.dialog_theme_mode_dark_label),
                description = stringResource(id = R.string.dialog_theme_mode_dark_description),
                onClick = { newThemeMode = ThemeMode.Dark }
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
                        onApplyRequest(newThemeMode)
                        onDismissRequest()
                    },
                    content = { Text(text = stringResource(id = R.string.dialog_action_apply)) }
                )

            }

        }

    }

}