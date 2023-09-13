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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

/**
 * Provides a [SystemUIBarsTweaker] instance down the UI content for consumption.
 * @param initialConfiguration the initial configuration.
 * @param systemUIBarsTweaker the SystemUIBarsTweaker instance.
 * @param content the UI content.
 **/
@Composable
fun ProvideSystemUIBarsTweaker(
    initialConfiguration: SystemUIBarsConfiguration = SystemUIBarsConfiguration.default(),
    systemUIBarsTweaker: SystemUIBarsTweaker = rememberSystemUIBarsTweaker(initialConfiguration = initialConfiguration),
    content: @Composable () -> Unit
) = CompositionLocalProvider(
    LocalSystemUIBarsTweaker provides systemUIBarsTweaker,
    content = content
)

/**
 * A CompositionLocal key used for consuming [SystemUIBarsTweaker] instance inside a composable.
 **/
val LocalSystemUIBarsTweaker = compositionLocalOf<SystemUIBarsTweaker> {
    error("CompositionLocal: LocalSystemUIBarsTweaker not provided.")
}