package com.stoyanvuchev.systemuibarstweaker.demo.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.ExamplesScreenDestinations
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.examplesNavigationGraph
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.settings.settingsNavigationGraph

/**
 * The navigation host of the app, containing all navigation graphs and screen destinations.
 *
 * @param modifier an optional modifier.
 * @param navHostController the navigation host controller of the app.
 * @param onDialogInvocation a callback to invoke a specific dialog.
 **/
@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onDialogInvocation: (InvokedDialog) -> Unit
) = NavHost(
    modifier = modifier.fillMaxSize(),
    navController = navHostController,
    startDestination = ExamplesScreenDestinations.navigationGraphRoute,
    builder = {

        examplesNavigationGraph(
            navHostController = navHostController,
            onDialogInvocation = onDialogInvocation
        )

        settingsNavigationGraph(
            navHostController = navHostController,
            onDialogInvocation = onDialogInvocation
        )

    }
)