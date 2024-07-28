package com.stoyanvuchev.systemuibarstweaker.demo.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.lerp
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffoldUtils
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.domain.model.settings.SettingsCategory
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.composable.ListCategorySeparator
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.etc.LocalPaddingValues
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ext.Empty

/**
 * A screen containing the settings of the app and components for navigating to related screens.
 *
 * @param onNavigateToScreen a callback for navigating to a specific screen.
 * @param onDialogInvocation a callback to invoke a specific dialog.
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateToScreen: (String) -> Unit,
    onDialogInvocation: (InvokedDialog) -> Unit
) {

    // Consuming the [PaddingValues] provided by the parent.
    val localPaddingValues = LocalPaddingValues.current

    // Retrieving the settings categories list.
    val settingsCategoriesList by rememberUpdatedState(SettingsCategory.categoryList())

    val layoutDirection = LocalLayoutDirection.current
    val settingsGridState = rememberLazyGridState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    // Transforming the [TextStyle] of the [TopAppBar] title.
    val responsiveTopAppBarTitleStyle by rememberUpdatedState(
        lerp(
            start = MaterialTheme.typography.displaySmall,
            stop = MaterialTheme.typography.titleLarge,
            fraction = scrollBehavior.state.collapsedFraction
        )
    )

    // Transforming the start padding of the [TopAppBar] title.
    val responsiveTopAppBarTitlePadding by rememberUpdatedState(
        androidx.compose.ui.unit.lerp(
            start = 16.dp,
            stop = 8.dp,
            fraction = scrollBehavior.state.collapsedFraction
        )
    )

    ResponsiveScaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = localPaddingValues.calculateStartPadding(layoutDirection)),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        topBar = {

            LargeTopAppBar(
                title = {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer {
                                translationX = responsiveTopAppBarTitlePadding.toPx()
                            },
                        text = stringResource(id = R.string.settings_screen_label),
                        style = responsiveTopAppBarTitleStyle
                    )

                },
                scrollBehavior = scrollBehavior,
                windowInsets = ResponsiveScaffoldUtils.topAppBarWindowInsets()
            )

        },
        contentWindowInsets = WindowInsets.Empty
    ) { paddingValues ->

        // Adding additional padding to the list items.
        val contentPadding by rememberUpdatedState(
            PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection) + 8.dp,
                top = paddingValues.calculateTopPadding() + 4.dp,
                end = paddingValues.calculateEndPadding(layoutDirection) + 8.dp,
                bottom = paddingValues.calculateBottomPadding() +
                        localPaddingValues.calculateBottomPadding() + 32.dp
            )
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            state = settingsGridState,
            columns = GridCells.Adaptive(minSize = 320.dp),
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            item(
                key = "introduction_text",
                span = { GridItemSpan(maxLineSpan) },
                content = {

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .padding(vertical = 16.dp),
                        text = stringResource(id = R.string.settings_screen_introduction),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }
            )

            settingsCategoriesList.forEach { category ->

                item(
                    key = "category_${category.label}",
                    span = { GridItemSpan(maxLineSpan) },
                    content = {

                        ListCategorySeparator(
                            label = { Text(text = stringResource(id = category.label)) }
                        )

                    }
                )

                items(
                    items = category.items,
                    key = { "category_item_$it" },
                    itemContent = { item ->

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.large)
                                .clickable(
                                    onClick = remember {
                                        {
                                            item.action.first?.let(onNavigateToScreen)
                                                ?: item.action.second?.let(onDialogInvocation)
                                                ?: Unit
                                        }
                                    }
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Spacer(modifier = Modifier.width(4.dp))

                            Icon(
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(2.dp),
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    text = stringResource(id = item.label),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onBackground
                                )

                                Text(
                                    text = stringResource(id = item.description),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .67f)
                                )

                            }

                        }

                    }
                )

            }

        }

    }

}