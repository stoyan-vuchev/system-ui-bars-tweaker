package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.etc

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.compositionLocalOf

/**
 * A CompositionLocal key for providing/consuming [PaddingValues].
 **/
val LocalPaddingValues = compositionLocalOf { PaddingValues() }