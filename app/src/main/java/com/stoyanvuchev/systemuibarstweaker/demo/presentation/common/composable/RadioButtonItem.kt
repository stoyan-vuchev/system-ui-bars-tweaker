package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

/**
 * A composable item for displaying a label and optional description to a [RadioButton].
 *
 * @param modifier an optional modifier,
 * @param selected the state indicating whether the radio button is selected/unselected.
 * @param label the label of the switch.
 * @param description an optional description of the switch.
 * @param enabled whether the radio button is enabled or disabled.
 * @param paddingValues an optional padding values.
 * @param onClick the callback invoked after the radio button is clicked.
 * @param selectedContent an optional content shown below the radio button after the radio button is selected.
 **/
@Composable
fun RadioButtonItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    label: String,
    description: String? = null,
    enabled: Boolean = true,
    paddingValues: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
    onClick: (() -> Unit)?,
    selectedContent: @Composable () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .then(modifier)
            .graphicsLayer(alpha = if (enabled) 1f else .5f)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(),
                    onClick = onClick!!,
                    role = Role.RadioButton,
                    enabled = enabled
                ),
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

            RadioButton(
                selected = selected,
                onClick = onClick,
                enabled = enabled
            )

        }

        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = selected,
            content = { selectedContent() },
            label = ""
        )

    }

}