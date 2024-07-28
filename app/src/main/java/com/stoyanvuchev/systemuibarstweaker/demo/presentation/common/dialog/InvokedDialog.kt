package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.dialog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Immutable
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.color_scheme.ColorSchemeDialog
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode.ThemeModeDialog

/**
 * A helper abstraction to determine invoked dialog type.
 **/
@Immutable
sealed interface InvokedDialog {

    /**
     * Indicates that there is no invocation.
     **/
    data object None : InvokedDialog

    /**
     * A dialog type indicating invocation of a [ColorSchemeDialog].
     **/
    @RequiresApi(Build.VERSION_CODES.S)
    data object InvokedColorSchemeDialog : InvokedDialog

    /**
     * A dialog type indicating invocation of a [ThemeModeDialog].
     **/
    data object InvokedThemeDialog : InvokedDialog

    /**
     * A dialog type indicating invocation of a [SystemUIBarsTweaksDialog].
     **/
    data object InvokedSystemUIBarsTweaksDialog : InvokedDialog

}