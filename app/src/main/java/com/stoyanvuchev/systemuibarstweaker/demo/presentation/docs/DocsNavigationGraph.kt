package com.stoyanvuchev.systemuibarstweaker.demo.presentation.docs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.docs.readme.DocsReadme

/**
 * The navigation graph associated with the documentation.
 *
 * @param navHostController the navigation host controller of the app.
 * @param onDialogInvocation a callback to invoke a specific dialog.
 **/
fun NavGraphBuilder.docsNavigationGraph(
    navHostController: NavHostController,
    onDialogInvocation: (InvokedDialog) -> Unit
) = navigation(
    startDestination = DocsScreenDestinations.Docs.route,
    route = DocsScreenDestinations.navigationGraphRoute,
    builder = {

        composable(
            route = DocsScreenDestinations.Docs.route,
            content = {

                DocsScreen(
                    onNavigateToDocs = { navHostController.navigate(route = it) }
                )

            }
        )

        composable(
            route = DocsScreenDestinations.Introduction.route,
            content = {

                DocsReadme()

            }
        )

    }
)