package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

/**
 * A composable item for displaying a label and optional description to a [Switch].
 *
 * @param modifier an optional modifier,
 * @param label the label of the switch.
 * @param description an optional description of the switch.
 * @param checked the state indicating whether the switch is checked/unchecked.
 * @param paddingValues an optional padding values.
 * @param onCheckedChange the callback invoked after the switch is checked/unchecked.
 **/
@Suppress("DEPRECATION_ERROR")
@Composable
fun ClickableSwitchItem(
    modifier: Modifier = Modifier,
    label: String,
    description: String? = null,
    checked: Boolean,
    paddingValues: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
    onCheckedChange: ((Boolean) -> Unit)?
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = { onCheckedChange!!(!checked) },
                role = Role.Switch
            )
            .padding(paddingValues)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            description?.let {

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .67f)
                )

            }

        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

    }

}