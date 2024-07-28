package com.stoyanvuchev.systemuibarstweaker.demo.domain.model.examples.photos

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable

/**
 * A data class containing a resource ID of a specific photo for the [PhotosExampleScreen] example.
 *
 * @property resId the resource ID of the photo.
 **/
@Stable
data class Photo(@DrawableRes val resId: Int)