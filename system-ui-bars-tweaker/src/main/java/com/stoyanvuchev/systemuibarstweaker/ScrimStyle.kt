/*
 * Copyright 2024 Stoyan Vuchev
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

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.stoyanvuchev.systemuibarstweaker.utils.ColorSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A custom class providing flexibility for system bars scrim.
 *
 * @param lightThemeColor The light theme scrim color.
 * @param darkThemeColor The dark theme scrim color.
 * @param lightThemeColorOpacity The opacity of the light theme scrim.
 * @param darkThemeColorOpacity The opacity of the dark theme scrim.
 */
@Serializable
@Stable
sealed class ScrimStyle(

    @Serializable(with = ColorSerializer::class)
    @SerialName("lightThemeColorBase")
    open val lightThemeColor: Color,

    @Serializable(with = ColorSerializer::class)
    @SerialName("darkThemeColorBase")
    open val darkThemeColor: Color,

    @SerialName("lightThemeColorOpacityBase")
    open val lightThemeColorOpacity: Float,

    @SerialName("darkThemeColorOpacityBase")
    open val darkThemeColorOpacity: Float

) {

    abstract fun copy(
        lightThemeColor: Color = this.lightThemeColor,
        darkThemeColor: Color = this.darkThemeColor,
        lightThemeColorOpacity: Float = this.lightThemeColorOpacity,
        darkThemeColorOpacity: Float = this.darkThemeColorOpacity
    ): ScrimStyle

    /**
     * An empty [ScrimStyle] object. Scrim will not be applied.
     */
    @Serializable
    data object None : ScrimStyle(
        lightThemeColor = Color.Unspecified,
        darkThemeColor = Color.Unspecified,
        lightThemeColorOpacity = 0f,
        darkThemeColorOpacity = 0f
    ) {
        override fun copy(
            lightThemeColor: Color,
            darkThemeColor: Color,
            lightThemeColorOpacity: Float,
            darkThemeColorOpacity: Float
        ) = None
    }

    /**
     * Applies the system scrim to the system bars.
     */
    @Serializable
    data object System : ScrimStyle(
        lightThemeColor = Color.Unspecified,
        darkThemeColor = Color.Unspecified,
        lightThemeColorOpacity = 0f,
        darkThemeColorOpacity = 0f
    ) {
        override fun copy(
            lightThemeColor: Color,
            darkThemeColor: Color,
            lightThemeColorOpacity: Float,
            darkThemeColorOpacity: Float
        ) = System
    }

    /**
     * Applies a custom scrim to the system bars.
     *
     * @param lightThemeColor The light theme scrim color.
     * @param darkThemeColor The dark theme scrim color.
     * @param lightThemeColorOpacity The opacity of the light theme scrim.
     * @param darkThemeColorOpacity The opacity of the dark theme scrim.
     */
    @Serializable
    class Custom(

        @Serializable(with = ColorSerializer::class)
        @SerialName("lightThemeColorDerived")
        override val lightThemeColor: Color = Color.White,

        @Serializable(with = ColorSerializer::class)
        @SerialName("darkThemeColorDerived")
        override val darkThemeColor: Color = Color.Black,

        @SerialName("lightThemeColorOpacityDerived")
        override val lightThemeColorOpacity: Float = .8f,

        @SerialName("darkThemeColorOpacityDerived")
        override val darkThemeColorOpacity: Float = .67f

    ) : ScrimStyle(
        lightThemeColor = lightThemeColor,
        darkThemeColor = darkThemeColor,
        lightThemeColorOpacity = lightThemeColorOpacity,
        darkThemeColorOpacity = darkThemeColorOpacity
    ) {
        override fun copy(
            lightThemeColor: Color,
            darkThemeColor: Color,
            lightThemeColorOpacity: Float,
            darkThemeColorOpacity: Float
        ) = Custom(
            lightThemeColor = lightThemeColor,
            darkThemeColor = darkThemeColor,
            lightThemeColorOpacity = lightThemeColorOpacity,
            darkThemeColorOpacity = darkThemeColorOpacity
        )
    }

    companion object {

        val ObsoleteApiStyle = Custom().copy(
            lightThemeColor = Custom().darkThemeColor,
            lightThemeColorOpacity = Custom().darkThemeColorOpacity
        )

    }

}


