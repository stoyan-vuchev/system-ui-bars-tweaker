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
import com.stoyanvuchev.systemuibarstweaker.utils.ColorSerializer
import kotlinx.serialization.Serializable

/**
 * A class for styling a System Bar.
 *
 * @param color the system bar color.
 * @param darkIcons whether to use dark icons or not for the system bar.
 * @param scrimStyle applies a scrim style to ensure contrast for the system bar.
 **/
@Serializable
@Stable
class SystemBarStyle(

    @Serializable(with = ColorSerializer::class)
    val color: Color = Color.Unspecified,
    val darkIcons: Boolean = color.luminance() >= .5f,

    @Deprecated(
        message = "Consider using the new 'scrimStyle' constructor parameter instead of " +
                "the old 'enforceContrast' because it will be removed in future releases.",
        level = DeprecationLevel.WARNING
    ) val enforceContrast: Boolean = true,

    val scrimStyle: ScrimStyle = ScrimStyle.None

) {

    companion object {

        /**
         * The default Status Bar [SystemBarStyle] with an optional overriding.
         *
         * @param color the system bar color.
         * @param darkIcons whether to use dark icons or not for the system bar.
         * @param scrimStyle applies a scrim style to ensure contrast for the system bar.
         **/
        @Composable
        fun defaultStatusBarStyle(
            color: Color = Color.Unspecified,
            darkIcons: Boolean = !isSystemInDarkTheme(),
            scrimStyle: ScrimStyle = ScrimStyle.None
        ) = SystemBarStyle(
            color = color,
            darkIcons = darkIcons,
            scrimStyle = scrimStyle
        )

        /**
         * The default Navigation Bar [SystemBarStyle] with an optional overriding.
         *
         * @param color the system bar color.
         * @param darkIcons whether to use dark icons or not for the system bar.
         * @param scrimStyle applies a scrim style to ensure contrast for the system bar.
         **/
        @Composable
        fun defaultNavigationBarStyle(
            color: Color = Color.Unspecified,
            darkIcons: Boolean = !isSystemInDarkTheme(),
            scrimStyle: ScrimStyle = when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> ScrimStyle.None
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> ScrimStyle.Custom()
                else -> ScrimStyle.ObsoleteApiStyle
            }
        ) = SystemBarStyle(
            color = color,
            darkIcons = darkIcons,
            scrimStyle = scrimStyle
        )

    }

    /**
     * Creates a copy of the [SystemBarStyle] with an optional overriding.
     *
     * @param color the system bar color.
     * @param darkIcons whether to use dark icons or not for the system bar.
     * @param scrimStyle applies a scrim style to ensure contrast for the system bar.
     **/
    @Suppress("Deprecation")
    fun copy(
        color: Color = this.color,
        darkIcons: Boolean = this.darkIcons,
        enforceContrast: Boolean = this.enforceContrast,
        scrimStyle: ScrimStyle = this.scrimStyle
    ) = SystemBarStyle(
        color = color,
        darkIcons = darkIcons,
        enforceContrast = enforceContrast,
        scrimStyle = scrimStyle
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is SystemBarStyle) return false
        if (color != other.color) return false
        if (darkIcons != other.darkIcons) return false
        if (scrimStyle != other.scrimStyle) return false
        return true
    }

    override fun hashCode(): Int {
        var hashCode = color.hashCode()
        hashCode = 31 * hashCode + darkIcons.hashCode()
        hashCode = 31 * hashCode + scrimStyle.hashCode()
        return hashCode
    }

    override fun toString(): String {
        return "SystemBarStyle(" +
                "color=$color, " +
                "darkIcons=$darkIcons, " +
                "scrimStyle=$scrimStyle" +
                ")"
    }

}

/**
 * The default Status Bar [SystemBarStyle] with an optional overriding.
 *
 * @param color the system bar color.
 * @param darkIcons whether to use dark icons or not for the system bar.
 * @param enforceContrast applies a scrim to ensure contrast for the system bar.
 */
@Deprecated(
    message = "Consider using the new 'scrimStyle' constructor parameter instead of " +
            "the old 'enforceContrast' because it will be removed in future releases.",
    level = DeprecationLevel.WARNING,
    replaceWith = ReplaceWith(
        """
            defaultStatusBarStyle(
                color: Color,
                darkIcons: Boolean,
                scrimStyle: ScrimStyle
            )
        """
    )
)
@JvmName("defaultStatusBarStyleDeprecated")
@Composable
fun SystemBarStyle.Companion.defaultStatusBarStyle(
    color: Color = Color.Unspecified,
    darkIcons: Boolean = !isSystemInDarkTheme(),
    enforceContrast: Boolean = false
) = SystemBarStyle(
    color = color,
    darkIcons = darkIcons,
    scrimStyle = if (enforceContrast) ScrimStyle.System else ScrimStyle.None
)

/**
 * The default Navigation Bar [SystemBarStyle] with an optional overriding.
 *
 * @param color the system bar color.
 * @param darkIcons whether to use dark icons or not for the system bar.
 * @param enforceContrast applies a scrim to ensure contrast for the system bar.
 */
@Deprecated(
    message = "Consider using the new 'scrimStyle' constructor parameter instead of " +
            "the old 'enforceContrast' because it will be removed in future releases.",
    level = DeprecationLevel.WARNING,
    replaceWith = ReplaceWith(
        """
            defaultNavigationBarStyle(
                color: Color,
                darkIcons: Boolean,
                scrimStyle: ScrimStyle
            )
        """
    )
)
@JvmName("defaultNavigationBarStyleDeprecated")
@Composable
fun SystemBarStyle.Companion.defaultNavigationBarStyle(
    color: Color = Color.Unspecified,
    darkIcons: Boolean = !isSystemInDarkTheme(),
    enforceContrast: Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.O
) = SystemBarStyle(
    color = color,
    darkIcons = darkIcons,
    scrimStyle = if (enforceContrast) when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> ScrimStyle.System
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> ScrimStyle.Custom()
        else -> ScrimStyle.ObsoleteApiStyle
    } else ScrimStyle.None
)

/**
 * Creates a copy of the [SystemBarStyle] with an optional overriding.
 *
 * @param color the system bar color.
 * @param darkIcons whether to use dark icons or not for the system bar.
 * @param enforceContrast applies a scrim to ensure contrast for the system bar.
 */
@Deprecated(
    message = "Consider using the new 'scrimStyle' constructor parameter instead of " +
            "the old 'enforceContrast' because it will be removed in future releases.",
    level = DeprecationLevel.WARNING,
    replaceWith = ReplaceWith(
        """
            copy(
                color: Color,
                darkIcons: Boolean,
                scrimStyle: ScrimStyle
            )
        """
    )
)
@JvmName("copyDeprecated")
fun SystemBarStyle.copy(
    color: Color = this.color,
    darkIcons: Boolean = this.darkIcons,
    enforceContrast: Boolean = this.scrimStyle != ScrimStyle.None
) = SystemBarStyle(
    color = color,
    darkIcons = darkIcons,
    scrimStyle = if (enforceContrast) ScrimStyle.System else ScrimStyle.None
)