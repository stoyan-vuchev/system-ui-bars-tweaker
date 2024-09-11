package com.stoyanvuchev.systemuibarstweaker.demo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.stoyanvuchev.responsive_scaffold.ResponsiveScaffold
import com.stoyanvuchev.systemuibarstweaker.demo.SystemUIBarsTweakerDemoApp.Companion.appModule
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.color_scheme.ColorSchemeDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog.InvokedColorSchemeDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog.InvokedSystemUIBarsTweaksDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog.InvokedThemeDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.InvokedDialog.None
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.SystemUIBarsScrimDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog.SystemUIBarsTweaksDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.etc.LocalPaddingValues
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode.ThemeModeDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ui.theme.ApplicationTheme
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.viewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navHostController = rememberNavController()
            val viewModel = viewModel<MainActivityViewModel>(
                factory = viewModelFactory {
                    val appPreferences by lazy { appModule.appPreferences }
                    MainActivityViewModel(appPreferences = appPreferences)
                }
            )

            val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()
            val colorScheme by viewModel.colorScheme.collectAsStateWithLifecycle()
            val invokedDialog by viewModel.invokedDialog.collectAsStateWithLifecycle()
            val systemUIBarsConfiguration by viewModel.systemUIBarsConfiguration.collectAsStateWithLifecycle()

            ApplicationTheme(
                themeMode = themeMode,
                colorScheme = colorScheme,
                systemUIBarsConfiguration = systemUIBarsConfiguration,
            ) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {

                    ResponsiveScaffold(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        bottomBar = { AppBottomNavigationBar(navController = navHostController) },
                        sideRail = { AppNavigationRail(navController = navHostController) }
                    ) { paddingValues ->

                        // Providing [paddingValues] to the navigation screens.
                        CompositionLocalProvider(
                            LocalPaddingValues provides paddingValues
                        ) {

                            AppNavigationHost(
                                navHostController = navHostController,
                                onDialogInvocation = viewModel::onDialogInvocation
                            )

                        }

                    }

                    when (invokedDialog) {

                        is None -> Unit

                        is InvokedThemeDialog -> ThemeModeDialog(
                            themeMode = themeMode,
                            onDismissRequest = remember { { viewModel.onDialogInvocation(None) } },
                            onApplyRequest = viewModel::onApplyThemeMode
                        )

                        is InvokedColorSchemeDialog -> ColorSchemeDialog(
                            colorScheme = colorScheme,
                            onDismissRequest = remember { { viewModel.onDialogInvocation(None) } },
                            onApplyRequest = viewModel::onApplyColorScheme
                        )

                        is InvokedSystemUIBarsTweaksDialog -> SystemUIBarsTweaksDialog(
                            systemUIBarsConfiguration = systemUIBarsConfiguration,
                            onDismissRequest = remember {
                                {
                                    viewModel.onDialogInvocation(None)
                                }
                            },
                            onShowScrimStyleDialog = remember {
                                {
                                    viewModel.onDialogInvocation(
                                        InvokedDialog.InvokedSystemUIBarsScrimDialog
                                    )
                                }
                            },
                            onApplyRequest = viewModel::onApplyNewSystemUIBarsConfiguration
                        )

                        is InvokedDialog.InvokedSystemUIBarsScrimDialog -> SystemUIBarsScrimDialog(
                            systemUIBarsConfiguration = systemUIBarsConfiguration,
                            onDismissRequest = remember {
                                {
                                    viewModel.onDialogInvocation(InvokedSystemUIBarsTweaksDialog)
                                }
                            },
                            onApplyRequest = viewModel::onApplyNewSystemUIBarsConfiguration
                        )

                    }

                }

            }
        }

    }

}