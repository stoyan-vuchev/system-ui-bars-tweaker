package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A composable for separating a specific group of items (category) from the rest.
 *
 * @param modifier an optional modifier.
 * @param label the label of the separated category.
 * @param horizontalGap the gap before the label and after the divider.
 * @param spacingGap the gap between the label and the divider.
 **/
@Composable
fun ListCategorySeparator(
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit,
    horizontalGap: Dp = 24.dp,
    spacingGap: Dp = 24.dp
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(horizontalGap))

        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.secondary,
            content = {

                ProvideTextStyle(
                    value = MaterialTheme.typography.bodySmall,
                    content = label
                )

            }
        )

        Spacer(modifier = Modifier.width(spacingGap))

        Box(
            modifier = Modifier
                .height(DividerDefaults.Thickness)
                .weight(1f)
                .background(
                    color = DividerDefaults.color,
                    shape = RoundedCornerShape(50)
                )
        )

        Spacer(modifier = Modifier.width(horizontalGap))

    }

}