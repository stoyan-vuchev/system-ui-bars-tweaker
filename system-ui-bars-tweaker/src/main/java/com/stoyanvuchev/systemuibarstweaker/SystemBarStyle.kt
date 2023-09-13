/*
 * Copyright 2023 Stoyan Vuchev
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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

/**
 * A class for styling a System Bar.
 *
 * @param color the system bar color.
 * @param darkIcons whether to use dark icons or not for the system bar.
 * @param enforceContrast applies a scrim to ensure contrast for the system bar.
 **/
@Stable
class SystemBarStyle(
    val color: Color,
    val darkIcons: Boolean = color.luminance() >= .5f,
    val enforceContrast: Boolean = true
) {

    companion object {

        /**
         * The default Status Bar [SystemBarStyle] with an optional overriding.
         *
         * @param color the system bar color.
         * @param darkIcons whether to use dark icons or not for the system bar.
         * @param enforceContrast applies a scrim to ensure contrast for the system bar.
         **/
        @Composable
        fun defaultStatusBarStyle(
            color: Color = Color.Transparent,
            darkIcons: Boolean = !isSystemInDarkTheme(),
            enforceContrast: Boolean = false
        ) = SystemBarStyle(
            color = color,
            darkIcons = darkIcons,
            enforceContrast = enforceContrast
        )

        /**
         * The default Navigation Bar [SystemBarStyle] with an optional overriding.
         *
         * @param color the system bar color.
         * @param darkIcons whether to use dark icons or not for the system bar.
         * @param enforceContrast applies a scrim to ensure contrast for the system bar.
         **/
        @Composable
        fun defaultNavigationBarStyle(
            color: Color = Color.Transparent,
            darkIcons: Boolean = !isSystemInDarkTheme(),
            enforceContrast: Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.O
        ) = SystemBarStyle(
            color = color,
            darkIcons = darkIcons,
            enforceContrast = enforceContrast
        )

    }

    /**
     * Creates a copy of the [SystemBarStyle] with an optional overriding.
     *
     * @param color the system bar color.
     * @param darkIcons whether to use dark icons or not for the system bar.
     * @param enforceContrast applies a scrim to ensure contrast for the system bar.
     **/
    fun copy(
        color: Color = this.color,
        darkIcons: Boolean = this.darkIcons,
        enforceContrast: Boolean = this.enforceContrast
    ) = SystemBarStyle(
        color = color,
        darkIcons = darkIcons,
        enforceContrast = enforceContrast
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is SystemBarStyle) return false
        if (color != other.color) return false
        if (darkIcons != other.darkIcons) return false
        if (enforceContrast != other.enforceContrast) return false
        return true
    }

    override fun hashCode(): Int {
        var hashCode = color.hashCode()
        hashCode = 31 * hashCode + darkIcons.hashCode()
        hashCode = 31 * hashCode + enforceContrast.hashCode()
        return hashCode
    }

    override fun toString(): String {
        return "SystemBarStyle(" +
                "color=$color," +
                "darkIcons=$darkIcons, " +
                "enforceContrast=$enforceContrast)"
    }

}