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
import kotlinx.serialization.Serializable

/**
 * A custom class providing flexibility for system bars scrim.
 */
@Serializable
@Stable
sealed class ScrimStyle {

    /**
     * An empty [ScrimStyle] object. Scrim will not be applied.
     */
    @Serializable
    data object None : ScrimStyle()

    /**
     * Applies the system scrim to the system bars.
     */
    @Serializable
    data object System : ScrimStyle()

    /**
     * Applies a custom scrim to the system bars.
     *
     * @param lightThemeColor The light theme scrim color.
     * @param darkThemeColor The dark theme scrim color.
     * @param lightThemeColorOpacity The opacity of the light theme scrim.
     * @param darkThemeColorOpacity The opacity of the dark theme scrim.
     */
    @Serializable
    data class Custom(

        @Serializable(with = ColorSerializer::class)
        val lightThemeColor: Color = Color.White,

        @Serializable(with = ColorSerializer::class)
        val darkThemeColor: Color = Color.Black,

        val lightThemeColorOpacity: Float = .8f,
        val darkThemeColorOpacity: Float = .67f

    ) : ScrimStyle()

}


