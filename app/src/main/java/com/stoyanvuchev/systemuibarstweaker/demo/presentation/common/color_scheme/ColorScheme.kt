package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.color_scheme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.runtime.compositionLocalOf
import com.stoyanvuchev.systemuibarstweaker.demo.R

/**
 * Constants for working with a color scheme.
 *
 * @param description the resource ID of the color scheme description.
 **/
enum class ColorScheme(
    @StringRes val description: Int
) {

    /**
     * A dynamic color scheme, applying the System colors.
     **/
    @RequiresApi(Build.VERSION_CODES.S)
    Dynamic(description = R.string.color_scheme_dynamic_description),

    /**
     * The default color scheme of the app.
     **/
    Default(description = R.string.color_scheme_default_description),

    /**
     * The default Material3 Lavender color scheme.
     **/
    Lavender(description = R.string.color_scheme_lavender_description);

    companion object {

        /**
         * Returns [ColorScheme.Dynamic] if API >= 31, otherwise [ColorScheme.Default].
         **/
        val System get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) Dynamic else Default

    }

}

/**
 * A CompositionLocal key for providing/consuming the currently applied [ColorScheme].
 **/
val LocalColorScheme = compositionLocalOf { ColorScheme.System }