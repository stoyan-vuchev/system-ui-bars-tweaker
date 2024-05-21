package com.stoyanvuchev.systemuibarstweaker.demo.domain.model.examples

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Article
import androidx.compose.material.icons.rounded.PhotoLibrary
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.RecommendedTweaks
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.ExamplesScreen
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.ExamplesScreenDestinations
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.ExamplesScreenItem
import kotlinx.collections.immutable.persistentListOf

/**
 * A data class containing the necessary properties for an [ExamplesScreenItem].
 *
 * @property route the navigation destination route of the example.
 * @property icon the icon of the example.
 * @property name the name of the example.
 * @property description the description of the example.
 * @property recommendedTweaks a list with recommended tweaks for the example.
 **/
@Stable
data class Example(
    val route: String,
    val icon: ImageVector,
    @StringRes val name: Int,
    @StringRes val description: Int,
    val recommendedTweaks: List<RecommendedTweaks>
) {

    companion object {

        /**
         * Returns an immutable list with examples for the [ExamplesScreen] items.
         **/
        val examplesList
            get() = persistentListOf(
                Example(
                    route = ExamplesScreenDestinations.Articles.route,
                    icon = Icons.Rounded.Article,
                    name = R.string.examples_articles_name,
                    description = R.string.examples_articles_description,
                    recommendedTweaks = persistentListOf(
                        RecommendedTweaks.TranslucentStatusBar,
                        RecommendedTweaks.TranslucentNavBar,
                        RecommendedTweaks.NavBarContrast
                    )
                ),
                Example(
                    route = ExamplesScreenDestinations.Photos.route,
                    icon = Icons.Rounded.PhotoLibrary,
                    name = R.string.examples_photos_name,
                    description = R.string.examples_photos_description,
                    recommendedTweaks = persistentListOf(
                        RecommendedTweaks.TranslucentStatusBar,
                        RecommendedTweaks.TranslucentNavBar,
                        RecommendedTweaks.NavBarContrast
                    )
                )
            )

    }

}