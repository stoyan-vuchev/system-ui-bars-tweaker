package com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.articles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.BuildCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffoldUtils
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.domain.model.examples.articles.Article
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog

/**
 * An example screen with some articles for demonstration.
 *
 * @param onNavigateUp a callback to navigate back to the previous screen.
 * @param onDialogInvocation a callback to invoke a specific dialog.
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesExampleScreen(
    onNavigateUp: () -> Unit,
    onDialogInvocation: (InvokedDialog) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val articlesGridState = rememberLazyGridState()

    ResponsiveScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        topBar = {

            TopAppBar(
                title = { Text(text = stringResource(id = R.string.examples_articles_name)) },
                navigationIcon = {

                    IconButton(
                        onClick = onNavigateUp,
                        content = {

                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "Navigate back."
                            )

                        }
                    )

                },
                actions = {

                    IconButton(
                        onClick = remember {
                            {
                                onDialogInvocation(InvokedDialog.InvokedSystemUIBarsTweaksDialog)
                            }
                        },
                        content = {

                            Icon(
                                imageVector = Icons.Rounded.BuildCircle,
                                contentDescription = "Show the System UI Bars Tweaks dialog."
                            )

                        }
                    )

                },
                scrollBehavior = scrollBehavior,
                windowInsets = ResponsiveScaffoldUtils.topAppBarWindowInsets()
            )

        }
    ) { paddingValues ->

        // Adding additional padding to the articles.
        val layoutDirection = LocalLayoutDirection.current
        val contentPadding by rememberUpdatedState(
            PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection) + 16.dp,
                top = paddingValues.calculateTopPadding() + 32.dp,
                end = paddingValues.calculateEndPadding(layoutDirection) + 16.dp,
                bottom = paddingValues.calculateBottomPadding() + 32.dp
            )
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            state = articlesGridState,
            columns = GridCells.Adaptive(300.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = contentPadding
        ) {

            items(
                items = Article.articlesList,
                key = { "article_$it" },
                itemContent = { ArticleItem(article = it) }
            )

        }

    }

}