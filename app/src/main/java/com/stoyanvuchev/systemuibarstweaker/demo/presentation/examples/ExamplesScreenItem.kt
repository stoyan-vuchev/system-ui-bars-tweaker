package com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.domain.model.examples.Example

/**
 * A composable for displaying [Example] item in the [ExamplesScreen].
 *
 * @param example the example item to be displayed.
 * @param onNavigateToExampleScreen a callback for navigating to the specific screen of the example.
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamplesScreenItem(
    example: Example,
    onNavigateToExampleScreen: (String) -> Unit
) {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = remember { { onNavigateToExampleScreen(example.route) } },
        shape = MaterialTheme.shapes.large
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = stringResource(id = example.name),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                imageVector = example.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )

        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = stringResource(id = example.description),
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider(modifier = Modifier.padding(horizontal = 20.dp))

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = stringResource(id = R.string.recommended_tweaks) + ":",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(4.dp))

        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {

            example.recommendedTweaks.forEach { recommendation ->

                Text(
                    text = "• " + stringResource(id = recommendation.value),
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }

        }

    }

}