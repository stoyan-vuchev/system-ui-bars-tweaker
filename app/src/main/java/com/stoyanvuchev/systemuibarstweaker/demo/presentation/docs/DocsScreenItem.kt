package com.stoyanvuchev.systemuibarstweaker.demo.presentation.docs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.systemuibarstweaker.demo.domain.model.docs.DocsItem

/**
 * A composable for displaying [DocsItem] item in the [DocsScreen].
 *
 * @param docs the docs item to be displayed.
 * @param onNavigateToDocs a callback for navigating to the specific screen of the docs.
 **/
@Composable
fun DocsScreenItem(
    docs: DocsItem,
    onNavigateToDocs: (String) -> Unit
) {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (docs.readmeRoute.isNotBlank()) {
                onNavigateToDocs(docs.readmeRoute)
            }
        },
        shape = MaterialTheme.shapes.large
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = docs.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

        HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp))

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = docs.description,
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

    }

}