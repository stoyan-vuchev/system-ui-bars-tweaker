package com.stoyanvuchev.systemuibarstweaker.demo.presentation.settings

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stoyanvuchev.systemuibarstweaker.LocalSystemUIBarsTweaker
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog

/**
 * The navigation graph associated with the settings of the app.
 *
 * @param navHostController the navigation host controller of the app.
 * @param onDialogInvocation a callback to invoke a specific dialog.
 **/
fun NavGraphBuilder.settingsNavigationGraph(
    navHostController: NavHostController,
    onDialogInvocation: (InvokedDialog) -> Unit
) = navigation(
    startDestination = SettingsScreenDestinations.Settings.route,
    route = SettingsScreenDestinations.navigationGraphRoute,
    builder = {

        composable(
            route = SettingsScreenDestinations.Settings.route,
            content = {

                val context = LocalContext.current
                val tweaker = LocalSystemUIBarsTweaker.current

                SettingsScreen(
                    onNavigateToScreen = { route ->
                        if (route == "test_gesture") { // fixme
                            val mode = "Navigation mode: " +
                                    (if (tweaker.isGestureNavigationEnabled) "Gesture"
                                    else "Button") + " Navigation"
                            Toast.makeText(
                                context,
                                mode,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else navHostController.navigate(route) { launchSingleTop = true }
                    },
                    onDialogInvocation = onDialogInvocation
                )

            }
        )

    }
)