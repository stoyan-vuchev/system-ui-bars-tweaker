package com.stoyanvuchev.systemuibarstweaker.demo.presentation.docs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.lerp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffoldUtils
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.domain.model.docs.DocsItem
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.etc.LocalPaddingValues
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ext.Empty

/**
 * A screen containing quick documentation guides and references.
 *
 * @param onNavigateToDocs a callback for navigating to a specific docs screen.
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocsScreen(
    onNavigateToDocs: (String) -> Unit
) {

    // Consuming the [PaddingValues] provided by the parent.
    val localPaddingValues = LocalPaddingValues.current

    val layoutDirection = LocalLayoutDirection.current
    val examplesGridState = rememberLazyGridState()
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
        lerp(
            start = 16.dp,
            stop = 4.dp,
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
                        text = stringResource(id = R.string.docs_screen_label),
                        style = responsiveTopAppBarTitleStyle
                    )

                },
                scrollBehavior = scrollBehavior,
                windowInsets = ResponsiveScaffoldUtils.topAppBarWindowInsets()
            )
        },
        contentWindowInsets = WindowInsets.Empty
    ) { paddingValues ->

        // Adding additional padding to the examples.
        val contentPadding by rememberUpdatedState(
            PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection) + 16.dp,
                top = paddingValues.calculateTopPadding() + 8.dp,
                end = paddingValues.calculateEndPadding(layoutDirection) + 16.dp,
                bottom = paddingValues.calculateBottomPadding() +
                        localPaddingValues.calculateBottomPadding() + 32.dp
            )
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            state = examplesGridState,
            columns = GridCells.Adaptive(minSize = 320.dp),
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item(
                key = "introduction_text",
                span = { GridItemSpan(maxLineSpan) },
                content = {

                    Text(
                        modifier = Modifier
                            .padding(top = 12.dp, bottom = 24.dp)
                            .padding(horizontal = 16.dp),
                        text = stringResource(id = R.string.docs_screen_introduction),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }
            )

            items(
                items = DocsItem.docsList,
                key = { "docs_$it" },
                itemContent = { docs ->

                    DocsScreenItem(
                        docs = docs,
                        onNavigateToDocs = onNavigateToDocs
                    )

                }
            )

        }

    }

}