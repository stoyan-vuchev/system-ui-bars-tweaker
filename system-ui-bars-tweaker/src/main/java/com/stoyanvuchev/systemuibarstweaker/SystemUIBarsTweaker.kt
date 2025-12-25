/*
 * Copyright 2023 - 2025 Stoyan Vuchev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stoyanvuchev.systemuibarstweaker

import android.app.Activity
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
import com.stoyanvuchev.systemuibarstweaker.utils.WindowUtils

/**
 * A Top-level composable that remembers a [SystemUIBarsTweaker] instance.
 *
 * By using a reference to the local [View] and its specific [Window],
 * the [SystemUIBarsTweaker] can be used to tweak the system bars on that specific [Window].
 *
 * Firstly, this method will attempt to find whether the local [View]'s parent is a [DialogWindowProvider].
 * Then the initial [SystemUIBarsConfiguration] will be applied to the dialog's [Window].
 *
 * Secondly, if the local [View]'s parent is not a [DialogWindowProvider],
 * then the method will attempt to find the [Window] of the [Activity]
 * that contains the local [View].
 *
 * Finally, if the local [View]'s [Window] is not null,
 * the method will create an [SystemUIBarsTweaker] instance with applied [SystemUIBarsConfiguration].
 *
 * Otherwise, tweaks won't be applied, without throwing any [Exception].
 * Therefore, the system bars won't have any tweaks made by the [SystemUIBarsTweaker].
 *
 * @param view a reference to the local view.
 * @param window a reference to the [view]'s window on which to apply the tweaks to.
 * @param initialConfiguration the initial configuration.
 **/
@Composable
fun rememberSystemUIBarsTweaker(
    view: View = LocalView.current,
    window: Window? = WindowUtils.findWindow(view),
    initialConfiguration: SystemUIBarsConfiguration = SystemUIBarsConfiguration.default()
) = retain<SystemUIBarsTweaker>(view, window) {
    SystemUIBarsTweakerImpl(
        view = view,
        initialConfiguration = initialConfiguration,
        window = window
    )
}

/**
 * An abstraction providing handful utilities for tweaking the System UI Bars.
 **/
@Stable
interface SystemUIBarsTweaker {

    /**
     * Returns true if Edge-To-Edge is enabled, false otherwise.
     **/
    val isEdgeToEdgeEnabled: Boolean

    /**
     * Returns the currently applied Status Bar [SystemBarStyle].
     **/
    val statusBarStyle: SystemBarStyle

    /**
     * Returns the currently applied Navigation Bar [SystemBarStyle].
     **/
    val navigationBarStyle: SystemBarStyle

    /**
     * Returns true if the Status Bar is visible, false otherwise.
     **/
    val isStatusBarVisible: Boolean

    /**
     * Returns true if the Navigation Bar is visible, false otherwise.
     **/
    val isNavigationBarVisible: Boolean

    /**
     * Returns the currently applied System Bars Behavior.
     * It should be one of the
     * [WindowInsetsControllerCompat] behavior constants:
     * [WindowInsetsControllerCompat.BEHAVIOR_DEFAULT] and
     * [WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE].
     **/
    val systemBarsBehavior: Int

    /**
     * Returns the currently applied Status Bar [SystemUIBarsConfiguration].
     **/
    val systemUIBarsConfiguration: SystemUIBarsConfiguration

    /**
     * Returns true when the Gesture Navigation mode is enabled, false otherwise.
     **/
    val isGestureNavigationEnabled: Boolean

    /**
     * Functionality that allows the UI to be drawn under the system bars (a.k.a. Edge-To-Edge).
     *
     * @param enabled whether to enable or disable Edge-To-Edge.
     **/
    fun enableEdgeToEdge(enabled: Boolean = true)

    /**
     * Functionality for tweaking the style of the Status Bar.
     *
     * @param statusBarStyle a Status Bar style to be applied.
     **/
    fun tweakStatusBarStyle(statusBarStyle: SystemBarStyle)

    /**
     * Functionality for tweaking the style of the Navigation Bar.
     *
     * @param navigationBarStyle a Navigation Bar style to be applied.
     **/
    fun tweakNavigationBarStyle(navigationBarStyle: SystemBarStyle)

    /**
     * Functionality for tweaking the style of the System Bars.
     *
     * @param statusBarStyle a Status Bar style to be applied.
     * @param navigationBarStyle a navigation bar style to be applied.
     **/
    fun tweakSystemBarsStyle(
        statusBarStyle: SystemBarStyle,
        navigationBarStyle: SystemBarStyle
    )

    /**
     * Functionality for tweaking the Status Bar visibility.
     *
     * @param isVisible whether the Status Bar should be visible or not.
     **/
    fun tweakStatusBarVisibility(isVisible: Boolean)

    /**
     * Functionality for tweaking the Navigation Bar visibility.
     *
     * @param isVisible whether the Navigation Bar should be visible or not.
     **/
    fun tweakNavigationBarVisibility(isVisible: Boolean)

    /**
     * Functionality for tweaking the behavior of the System Bars.
     *
     * @param behavior the behavior should be one of the
     * [WindowInsetsControllerCompat] behavior constants:
     * [WindowInsetsControllerCompat.BEHAVIOR_DEFAULT] and
     * [WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE].
     **/
    fun tweakSystemBarsBehavior(behavior: Int)

    /**
     * Functionality for tweaking the [SystemUIBarsConfiguration].
     *
     * @param systemUIBarsConfiguration the configuration to be applied.
     **/
    fun tweakSystemUIBarsConfiguration(systemUIBarsConfiguration: SystemUIBarsConfiguration)

}

/**
 * An implementation of the [SystemUIBarsTweaker] abstraction
 * containing the necessary logic for applying tweaks.
 *
 * @param view a reference to the local view.
 * @param initialConfiguration the initial configuration.
 * @param window a reference to the [View]'s window on which to apply the tweaks to.
 **/
@Suppress("Deprecation")
internal class SystemUIBarsTweakerImpl(
    view: View,
    private val initialConfiguration: SystemUIBarsConfiguration,
    private val window: Window?
) : SystemUIBarsTweaker {

    // Attempt to retrieve an instance of the original [WindowInsetsCompat].
    private val rootWindowInsets = window?.let {
        ViewCompat.getRootWindowInsets(view)
    }

    // Attempt to retrieve a [WindowInsetsControllerCompat] instance.
    private val windowInsetsController = window?.let {
        WindowCompat.getInsetsController(it, view)
    }

    override var isStatusBarVisible =
        rootWindowInsets?.isVisible(WindowInsetsCompat.Type.statusBars()) == true
        private set

    override var isNavigationBarVisible =
        rootWindowInsets?.isVisible(WindowInsetsCompat.Type.navigationBars()) == true
        private set

    override var isEdgeToEdgeEnabled = !view.fitsSystemWindows
        private set

    override var statusBarStyle = initialConfiguration.statusBarStyle
        private set

    override var navigationBarStyle = initialConfiguration.navigationBarStyle
        private set

    override var systemBarsBehavior = windowInsetsController?.systemBarsBehavior
        ?: BEHAVIOR_DEFAULT
        private set

    override var systemUIBarsConfiguration = initialConfiguration

    // Attempt to retrieve whether the Gesture Navigation mode is enabled.
    override val isGestureNavigationEnabled: Boolean = window?.run {
        try {
            Settings.Secure.getInt(
                window.context.contentResolver,
                "navigation_mode"
            ) == 2 // 2 means gesture navigation, 1 means button navigation
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
            false // If setting is not found, default to false
        }
    } ?: false

    // Attempt to enable or disable Edge-To-Edge if the [window] is not null.
    override fun enableEdgeToEdge(enabled: Boolean) {
        window?.run {
            WindowCompat.setDecorFitsSystemWindows(window, !enabled)
        }?.let { this.isEdgeToEdgeEnabled = enabled }
    }

    // Attempt to apply Status Bar style.
    override fun tweakStatusBarStyle(statusBarStyle: SystemBarStyle) {

        val scrimStyle = statusBarStyle.scrimStyle
        val statusBarColor = when (scrimStyle) {

            is ScrimStyle.None -> statusBarStyle.color

            is ScrimStyle.System -> when {

                // Android 10 (API 29) or newer handles the System scrim.
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> Color.Unspecified

                else -> if (statusBarStyle.darkIcons) {
                    applyWhiteScrim(statusBarStyle.color)
                } else {
                    applyBlackScrim(statusBarStyle.color)
                }

            }

            is ScrimStyle.Custom -> if (statusBarStyle.darkIcons) {
                scrimStyle.lightThemeColor
                    .copy(alpha = scrimStyle.lightThemeColorOpacity)
            } else {
                scrimStyle.darkThemeColor
                    .copy(alpha = scrimStyle.darkThemeColorOpacity)
            }

        }.toArgb()

        when {

            // Android 10 (API 29) or newer.
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {

                // Attempt to apply the Status Bar's color & scrim.
                window?.run {
                    this.statusBarColor = statusBarColor
                    this.isStatusBarContrastEnforced = scrimStyle == ScrimStyle.System
                }

                // Attempt to apply the Status Bar's dark icons.
                windowInsetsController?.run {
                    this.isAppearanceLightStatusBars = statusBarStyle.darkIcons
                }

            }

            // Android 6.0 (API 23) or newer.
            else -> {

                // Attempt to apply the Status Bar's color.
                window?.run {
                    this.statusBarColor = statusBarColor
                }

                // Attempt to apply the Status Bar's dark icons.
                windowInsetsController?.run {
                    this.isAppearanceLightStatusBars = statusBarStyle.darkIcons
                }

            }

        }?.let { this.statusBarStyle = statusBarStyle }
    }

    // Attempt to apply Navigation Bar style.
    override fun tweakNavigationBarStyle(navigationBarStyle: SystemBarStyle) {

        val scrimStyle = navigationBarStyle.scrimStyle
        val navigationBarColor = when (scrimStyle) {

            is ScrimStyle.None -> navigationBarStyle.color

            is ScrimStyle.System -> when {

                // Android 10 (API 29) or newer handles the System scrim.
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> Color.Transparent

                Build.VERSION.SDK_INT < Build.VERSION_CODES.O -> {
                    applyBlackScrim(navigationBarStyle.color)
                }

                else -> if (navigationBarStyle.darkIcons) {
                    applyWhiteScrim(navigationBarStyle.color)
                } else {
                    applyBlackScrim(navigationBarStyle.color)
                }

            }

            is ScrimStyle.Custom -> if (navigationBarStyle.darkIcons) {
                scrimStyle.lightThemeColor.copy(alpha = scrimStyle.lightThemeColorOpacity)
            } else {
                scrimStyle.darkThemeColor.copy(alpha = scrimStyle.darkThemeColorOpacity)
            }

        }.toArgb()

        when {

            // Android 10 (API 29) or newer.
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {

                // Attempt to apply the Navigation Bar's color & scrim.
                window?.run {
                    this.navigationBarColor = navigationBarColor
                    this.isNavigationBarContrastEnforced =
                        !isGestureNavigationEnabled && scrimStyle == ScrimStyle.System
                }

                // Attempt to apply the Navigation Bar's dark icons.
                windowInsetsController?.run {
                    this.isAppearanceLightNavigationBars = navigationBarStyle.darkIcons
                }

            }

            // Android 8.0.0 (API 26) or newer.
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {

                // Attempt to apply the Navigation Bar's color.
                window?.run {
                    this.navigationBarColor = navigationBarColor
                }

                // Attempt to apply the Navigation Bar's dark icons.
                windowInsetsController?.run {
                    this.isAppearanceLightNavigationBars = navigationBarStyle.darkIcons
                }

            }

            // Android 6.0 (API 23) or newer.
            else -> {

                // Attempt to apply the Navigation Bar's color.
                window?.run {
                    this.navigationBarColor = navigationBarColor
                }

            }

        }?.let { this.navigationBarStyle = navigationBarStyle }
    }

    // Attempt to apply Status Bar & Navigation Bar styles.
    override fun tweakSystemBarsStyle(
        statusBarStyle: SystemBarStyle,
        navigationBarStyle: SystemBarStyle
    ) {
        tweakStatusBarStyle(statusBarStyle)
        tweakNavigationBarStyle(navigationBarStyle)
    }

    // Attempt to tweak the Status Bar visibility.
    override fun tweakStatusBarVisibility(isVisible: Boolean) {
        windowInsetsController?.run {
            if (isVisible) this.show(WindowInsetsCompat.Type.statusBars())
            else this.hide(WindowInsetsCompat.Type.statusBars())
        }?.let { this.isStatusBarVisible = isVisible }
    }

    // Attempt to tweak the Navigation Bar visibility.
    override fun tweakNavigationBarVisibility(isVisible: Boolean) {
        windowInsetsController?.run {
            if (isVisible) this.show(WindowInsetsCompat.Type.navigationBars())
            else this.hide(WindowInsetsCompat.Type.navigationBars())
        }?.let { this.isNavigationBarVisible = isVisible }
    }

    // Attempt to tweak the System Bars behavior.
    override fun tweakSystemBarsBehavior(behavior: Int) {
        windowInsetsController?.run {
            this.systemBarsBehavior = behavior
        }?.let { this.systemBarsBehavior = behavior }
    }

    // Attempt to tweak th System UI Bars Configuration.
    override fun tweakSystemUIBarsConfiguration(
        systemUIBarsConfiguration: SystemUIBarsConfiguration
    ) {
        enableEdgeToEdge(systemUIBarsConfiguration.enableEdgeToEdge)
        tweakStatusBarStyle(systemUIBarsConfiguration.statusBarStyle)
        tweakNavigationBarStyle(systemUIBarsConfiguration.navigationBarStyle)
        tweakSystemBarsBehavior(systemUIBarsConfiguration.systemBarsBehavior)
        tweakStatusBarVisibility(systemUIBarsConfiguration.isStatusBarVisible)
        tweakStatusBarVisibility(systemUIBarsConfiguration.isNavigationBarVisible)
    }

}

/**
 * Applies a Black scrim with opacity of 33% on top of a [Color].
 **/
internal val applyBlackScrim = applyScrim(scrim = Color.Black.copy(alpha = .33f))

/**
 * Applies a White scrim with opacity of 67% on top of a [Color].
 **/
internal val applyWhiteScrim = applyScrim(scrim = Color.White.copy(alpha = .67f))

/**
 * Applies a scrim on top of a given [Color].
 *
 * @param scrim the scrim color.
 **/
private fun applyScrim(scrim: Color): (Color) -> Color = { scrim.compositeOver(it) }