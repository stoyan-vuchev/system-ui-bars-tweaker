package com.stoyanvuchev.systemuibarstweaker.demo.presentation.docs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.runtime.Immutable
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ScreenDestination

/**
 * Contains the screen destinations of all docs.
 **/
@Immutable
object DocsScreenDestinations {

    /**
     * The screen destination of the [DocsScreen].
     **/
    data object Docs : ScreenDestination(
        route = "docs_screen_route",
        label = R.string.docs_screen_label,
        icon = Icons.Rounded.Code
    )

    data object Introduction : ScreenDestination(
        route = "docs_introduction_route",
        label = R.string.docs_screen_label
    )

    /**
     * The unique route of the [docsNavigationGraph].
     **/
    val navigationGraphRoute get() = "docs_navigation_graph"

}