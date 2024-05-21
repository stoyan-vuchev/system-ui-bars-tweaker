package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.color_scheme

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
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
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
 * A dialog for tweaking the [ColorScheme] of the app.
 *
 * @param colorScheme the currently applied color scheme.
 * @param onDismissRequest the callback invoked when the dialog is dismissed.
 * @param onApplyRequest the callback with the color scheme to be applied.
 **/
@Composable
fun ColorSchemeDialog(
    colorScheme: ColorScheme,
    onDismissRequest: () -> Unit,
    onApplyRequest: (ColorScheme) -> Unit
) {

    var newColorScheme by remember { mutableStateOf(colorScheme) }

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
                text = stringResource(id = R.string.dialog_color_scheme_title),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            Divider(
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .clip(RoundedCornerShape(50))
            )

            Spacer(modifier = Modifier.height(6.dp))

            @Stable
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

                RadioButtonItem(
                    selected = newColorScheme == ColorScheme.Dynamic,
                    label = stringResource(id = R.string.dialog_color_scheme_dynamic_label),
                    description = stringResource(id = R.string.dialog_color_scheme_dynamic_description),
                    onClick = { newColorScheme = ColorScheme.Dynamic }
                )

                Divider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))

            }

            RadioButtonItem(
                selected = newColorScheme == ColorScheme.Default,
                label = stringResource(id = R.string.dialog_color_scheme_default_label),
                description = stringResource(id = R.string.dialog_color_scheme_default_description),
                onClick = { newColorScheme = ColorScheme.Default }
            )

            Divider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))

            RadioButtonItem(
                selected = newColorScheme == ColorScheme.Lavender,
                label = stringResource(id = R.string.dialog_color_scheme_lavender_label),
                description = stringResource(id = R.string.dialog_color_scheme_lavender_description),
                onClick = { newColorScheme = ColorScheme.Lavender }
            )

            Spacer(modifier = Modifier.height(8.dp))

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
                        onApplyRequest(newColorScheme)
                        onDismissRequest()
                    },
                    content = { Text(text = stringResource(id = R.string.dialog_action_apply)) }
                )

            }

        }

    }

}