package com.stoyanvuchev.systemuibarstweaker.demo.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ScreenDestination
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ext.navigateToScreen

@Composable
fun AppBottomNavigationBar(
    navController: NavHostController,
    currentNavRoute: String? = navController.currentBackStackEntryAsState().value?.destination?.route,
    navDestinationList: List<ScreenDestination> = AppNavigationDestinations.navigationDestinations,
    visible: Boolean = navDestinationList.any { it.route == currentNavRoute }
) {

    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth(),
        visible = visible,
        enter = slideInVertically(animationSpec = spring(stiffness = Spring.StiffnessMediumLow)) { it },
        exit = slideOutVertically(animationSpec = spring(stiffness = Spring.StiffnessMedium)) { it }
    ) {

        NavigationBar {

            navDestinationList.forEach { destination ->

                NavigationBarItem(
                    selected = destination.route == currentNavRoute,
                    icon = {

                        Icon(
                            imageVector = destination.icon!!,
                            contentDescription = null
                        )

                    },
                    label = { Text(text = stringResource(id = destination.label!!)) },
                    onClick = {
                        if (currentNavRoute != destination.route) {
                            navController.navigateToScreen(
                                destinationRoute = destination.route,
                                popUpToRoute = navDestinationList.first().route
                            )
                        }
                    }
                )

            }

        }

    }

}