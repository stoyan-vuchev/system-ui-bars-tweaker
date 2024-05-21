package com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Category
import androidx.compose.runtime.Immutable
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ScreenDestination
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.articles.ArticlesExampleScreen
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.photos.PhotosExampleScreen

/**
 * Contains the screen destinations of all examples.
 **/
@Immutable
object ExamplesScreenDestinations {

    /**
     * The screen destination of the [ExamplesScreen].
     **/
    data object Examples : ScreenDestination(
        route = "examples_route",
        label = R.string.examples_screen_label,
        icon = Icons.Rounded.Category
    )

    /**
     * The screen destination of the [ArticlesExampleScreen].
     **/
    data object Articles : ScreenDestination(route = "examples_articles_route")

    /**
     * The screen destination of the [PhotosExampleScreen].
     **/
    data object Photos : ScreenDestination(route = "examples_photos_route")

    /**
     * The unique route of the [examplesNavigationGraph].
     **/
    val navigationGraphRoute: String get() = "examples_navigation_graph"

}