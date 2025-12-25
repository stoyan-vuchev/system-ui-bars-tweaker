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

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.core.view.WindowInsetsControllerCompat
import kotlinx.serialization.Serializable

/**
 * A class containing a variety of configurations for a [SystemUIBarsTweaker].
 *
 * @param enableEdgeToEdge whether to enable Edge-To-Edge or not.
 * @param statusBarStyle the status bar style.
 * @param navigationBarStyle the navigation bar style.
 * @param systemBarsBehavior the System Bars behavior.
 *
 * It should be one of the
 * [WindowInsetsControllerCompat] behavior constants:
 * [WindowInsetsControllerCompat.BEHAVIOR_DEFAULT] and
 * [WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE].
 **/
@Serializable
@Stable
class SystemUIBarsConfiguration(
    val isStatusBarVisible: Boolean,
    val isNavigationBarVisible: Boolean,
    val enableEdgeToEdge: Boolean,
    val statusBarStyle: SystemBarStyle,
    val navigationBarStyle: SystemBarStyle,
    val systemBarsBehavior: Int
) {

    companion object {

        /**
         * The default [SystemUIBarsTweaker] configuration with an optional overriding.
         *
         * @param enableEdgeToEdge whether to enable Edge-To-Edge or not.
         * @param statusBarStyle the status bar style.
         * @param navigationBarStyle the navigation bar style.
         * @param systemBarsBehavior the System Bars behavior.
         *
         * It should be one of the
         * [WindowInsetsControllerCompat] behavior constants:
         * [WindowInsetsControllerCompat.BEHAVIOR_DEFAULT] and
         * [WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE].
         **/
        @Composable
        fun default(
            isStatusBarVisible: Boolean = true,
            isNavigationBarVisible: Boolean = true,
            enableEdgeToEdge: Boolean = true,
            statusBarStyle: SystemBarStyle = SystemBarStyle.defaultStatusBarStyle(),
            navigationBarStyle: SystemBarStyle = SystemBarStyle.defaultNavigationBarStyle(),
            systemBarsBehavior: Int = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
        ) = SystemUIBarsConfiguration(
            isStatusBarVisible = isStatusBarVisible,
            isNavigationBarVisible = isNavigationBarVisible,
            enableEdgeToEdge = enableEdgeToEdge,
            statusBarStyle = statusBarStyle,
            navigationBarStyle = navigationBarStyle,
            systemBarsBehavior = systemBarsBehavior
        )

        val DEFAULT
            get() = SystemUIBarsConfiguration(
                isStatusBarVisible = true,
                isNavigationBarVisible = true,
                enableEdgeToEdge = true,
                statusBarStyle = SystemBarStyle().copy(scrimStyle = ScrimStyle.None),
                navigationBarStyle = SystemBarStyle().copy(
                    scrimStyle = when {
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> ScrimStyle.None
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> ScrimStyle.Custom()
                        else -> ScrimStyle.ObsoleteApiStyle
                    }
                ),
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            )

    }

    /**
     * Creates a copy of the [SystemUIBarsConfiguration] with an optional overriding.
     *
     * @param enableEdgeToEdge whether to enable Edge-To-Edge or not.
     * @param statusBarStyle the status bar style.
     * @param navigationBarStyle the navigation bar style.
     * @param systemBarsBehavior the System Bars behavior.
     *
     * It should be one of the
     * [WindowInsetsControllerCompat] behavior constants:
     * [WindowInsetsControllerCompat.BEHAVIOR_DEFAULT] and
     * [WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE].
     **/
    fun copy(
        isStatusBarVisible: Boolean = this.isStatusBarVisible,
        isNavigationBarVisible: Boolean = this.isNavigationBarVisible,
        enableEdgeToEdge: Boolean = this.enableEdgeToEdge,
        statusBarStyle: SystemBarStyle = this.statusBarStyle,
        navigationBarStyle: SystemBarStyle = this.navigationBarStyle,
        systemBarsBehavior: Int = this.systemBarsBehavior
    ) = SystemUIBarsConfiguration(
        isStatusBarVisible = isStatusBarVisible,
        isNavigationBarVisible = isNavigationBarVisible,
        enableEdgeToEdge = enableEdgeToEdge,
        statusBarStyle = statusBarStyle,
        navigationBarStyle = navigationBarStyle,
        systemBarsBehavior = systemBarsBehavior
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is SystemUIBarsConfiguration) return false
        if (isStatusBarVisible != other.isStatusBarVisible) return false
        if (isNavigationBarVisible != other.isNavigationBarVisible) return false
        if (enableEdgeToEdge != other.enableEdgeToEdge) return false
        if (statusBarStyle != other.statusBarStyle) return false
        if (navigationBarStyle != other.navigationBarStyle) return false
        if (systemBarsBehavior != other.systemBarsBehavior) return false
        return true
    }

    override fun hashCode(): Int {
        var hashCode = isStatusBarVisible.hashCode()
        hashCode = 31 * hashCode + isNavigationBarVisible.hashCode()
        hashCode = 31 * hashCode + enableEdgeToEdge.hashCode()
        hashCode = 31 * hashCode + statusBarStyle.hashCode()
        hashCode = 31 * hashCode + navigationBarStyle.hashCode()
        hashCode = 31 * hashCode + systemBarsBehavior.hashCode()
        return hashCode
    }

    override fun toString(): String {
        return "InitialConfiguration(" +
                "isStatusBarVisible=$isStatusBarVisible, " +
                "isNavigationBarVisible=$isNavigationBarVisible, " +
                "enableEdgeToEdge=$enableEdgeToEdge, " +
                "statusBarStyle=$statusBarStyle, " +
                "navigationBarStyle=$navigationBarStyle, " +
                "systemBarsBehavior=$systemBarsBehavior)"
    }

}