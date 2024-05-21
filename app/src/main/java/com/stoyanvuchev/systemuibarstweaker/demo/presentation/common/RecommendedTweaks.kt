package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common

import androidx.annotation.StringRes
import com.stoyanvuchev.systemuibarstweaker.demo.R

/**
 * An enum class containing constants for recommended tweaks.
 *
 * @param value the resource ID of the tweak recommendation string.
 **/
enum class RecommendedTweaks(
    @StringRes val value: Int
) {
    TranslucentStatusBar(value = R.string.recommended_tweaks_translucent_status_bar),
    TranslucentNavBar(value = R.string.recommended_tweaks_translucent_nav_bar),
    NavBarContrast(value = R.string.recommended_tweaks_nav_bar_contrast);
}