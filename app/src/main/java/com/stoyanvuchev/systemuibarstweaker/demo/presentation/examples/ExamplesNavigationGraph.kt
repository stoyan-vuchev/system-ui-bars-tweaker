package com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.articles.ArticlesExampleScreen
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.photos.PhotosExampleScreen

/**
 * The navigation graph associated with the examples of the app.
 *
 * @param navHostController the navigation host controller of the app.
 * @param onDialogInvocation a callback to invoke a specific dialog.
 **/
fun NavGraphBuilder.examplesNavigationGraph(
    navHostController: NavHostController,
    onDialogInvocation: (InvokedDialog) -> Unit
) = navigation(
    startDestination = ExamplesScreenDestinations.Examples.route,
    route = ExamplesScreenDestinations.navigationGraphRoute,
    builder = {

        composable(
            route = ExamplesScreenDestinations.Examples.route,
            content = {

                ExamplesScreen(
                    onNavigateToScreen = { route ->
                        navHostController.navigate(route) { launchSingleTop = true }
                    }
                )

            }
        )

        composable(
            route = ExamplesScreenDestinations.Articles.route,
            content = {

                ArticlesExampleScreen(
                    onNavigateUp = navHostController::navigateUp,
                    onDialogInvocation = onDialogInvocation
                )

            }
        )

        composable(
            route = ExamplesScreenDestinations.Photos.route,
            content = {

                PhotosExampleScreen(
                    onNavigateUp = navHostController::navigateUp,
                    onDialogInvocation = onDialogInvocation
                )

            }
        )

    }
)