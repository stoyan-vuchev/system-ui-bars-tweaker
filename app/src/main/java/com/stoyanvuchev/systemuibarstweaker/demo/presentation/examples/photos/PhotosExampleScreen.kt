package com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.photos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.BuildCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsControllerCompat
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffoldUtils
import com.stoyanvuchev.systemuibarstweaker.LocalSystemUIBarsTweaker
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.domain.model.examples.photos.PhotoCategory
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.composable.ListCategorySeparator
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ext.Empty
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ext.animateWindowInsets

/**
 * An example screen with some photos for demonstration.
 *
 * @param onNavigateUp a callback to navigate back to the previous screen.
 * @param onDialogInvocation a callback to invoke a specific dialog.
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosExampleScreen(
    onNavigateUp: () -> Unit,
    onDialogInvocation: (InvokedDialog) -> Unit
) {

    val tweaker = LocalSystemUIBarsTweaker.current
    val photosGridState = rememberLazyGridState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val isTopAppBarCollapsed by remember {
        derivedStateOf { scrollBehavior.state.collapsedFraction == 1f }
    }

    val topAppBarInsets by animateWindowInsets(
        targetValue = if (isTopAppBarCollapsed) WindowInsets.Empty
        else ResponsiveScaffoldUtils.topAppBarWindowInsets()
    )

    DisposableEffect(tweaker, isTopAppBarCollapsed) {

        tweaker.tweakSystemBarsBehavior(
            if (!isTopAppBarCollapsed) WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            else WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        )

        tweaker.tweakStatusBarVisibility(!isTopAppBarCollapsed)
        tweaker.tweakNavigationBarVisibility(!isTopAppBarCollapsed)

        // Restore the initial System UI Bars state on exit.
        onDispose {
            tweaker.tweakSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_DEFAULT)
            tweaker.tweakStatusBarVisibility(true)
            tweaker.tweakNavigationBarVisibility(true)
        }

    }

    ResponsiveScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        topBar = {

            TopAppBar(
                title = {

                    Text(text = stringResource(id = R.string.examples_photos_name))

                },
                navigationIcon = {

                    IconButton(
                        onClick = onNavigateUp,
                        content = {

                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
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
                windowInsets = topAppBarInsets
            )

        }
    ) { paddingValues ->

        // Adding additional padding to the photos.
        val layoutDirection = LocalLayoutDirection.current
        val contentPadding by rememberUpdatedState(
            PaddingValues(
                start = paddingValues.calculateStartPadding(layoutDirection) + 16.dp,
                top = paddingValues.calculateTopPadding() + 8.dp,
                end = paddingValues.calculateEndPadding(layoutDirection) + 16.dp,
                bottom = paddingValues.calculateBottomPadding() + 128.dp
            )
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            state = photosGridState,
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = contentPadding
        ) {

            PhotoCategory.categoryList.forEach { category ->

                item(
                    key = "category_label_${category.label}",
                    span = { GridItemSpan(maxLineSpan) },
                    content = {

                        ListCategorySeparator(
                            horizontalGap = 8.dp,
                            label = {

                                Text(
                                    text = stringResource(id = category.label),
                                    style = MaterialTheme.typography.titleMedium
                                )

                            }
                        )

                    }
                )

                items(
                    items = category.photos,
                    key = { "category_label_${category.label}_photo_$it" },
                    itemContent = { photo ->

                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(MaterialTheme.shapes.medium),
                            painter = painterResource(id = photo.resId),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )

                    }
                )

            }

        }

    }

}