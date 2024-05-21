package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ext

import androidx.navigation.NavHostController
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.AppBottomNavigationBar
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.AppNavigationRail

/**
 * An extension function to navigate to a specific screen
 * on the [AppBottomNavigationBar] or [AppNavigationRail].
 *
 * @param destinationRoute the route of the screen destination to navigate to.
 * @param popUpToRoute the route of the screen destination to pop the stack.
 **/
fun NavHostController.navigateToScreen(
    destinationRoute: String,
    popUpToRoute: String
) = navigate(route = destinationRoute) {
    launchSingleTop = true
    popUpTo(route = popUpToRoute) { inclusive = false }
}