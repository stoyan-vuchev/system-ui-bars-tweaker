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

package com.stoyanvuchev.systemuibarstweaker.utils

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * A custom [KSerializer] serializer for a [androidx.compose.ui.graphics.Color] class.
 * Feel free to reuse it when necessary. e.g. in a class stored in DataStore Preferences.
 *
 * Remember to annotate all constructor parameters of type [androidx.compose.ui.graphics.Color]
 * with:
 *
 * `@Serializable(with = ColorSerializer::class)`
 * ```
 * @Serializable
 * class SystemBarStyle(
 *     @Serializable(with = ColorSerializer::class) val color: Color = Color.Unspecified
 * )
 *```
 */
object ColorSerializer : KSerializer<Color> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "Color",
        kind = PrimitiveKind.INT
    )

    // Serialize Color as an ARGB Int.
    override fun serialize(encoder: Encoder, value: Color) {
        @ColorInt val colorInt = value.toArgb()
        encoder.encodeInt(colorInt)
    }

    // Deserialize the ARGB Int back to Color.
    override fun deserialize(decoder: Decoder): Color {
        @ColorInt val colorInt = decoder.decodeInt()
        return Color(colorInt)
    }

}