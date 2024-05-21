package com.stoyanvuchev.systemuibarstweaker.demo.domain.model.examples.photos

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.stoyanvuchev.systemuibarstweaker.demo.R
import kotlinx.collections.immutable.persistentListOf

/**
 * A data class containing a list of [Photo]s for the [PhotosExampleScreen] example.
 *
 * @property label the resource ID of the category label.
 * @property photos the list of photos.
 **/
@Stable
data class PhotoCategory(
    @StringRes val label: Int,
    val photos: List<Photo>
) {

    companion object {

        /**
         * Returns an immutable list of the photo categories for the [PhotosExampleScreen] example.
         **/
        val categoryList = persistentListOf(
            PhotoCategory(
                label = R.string.examples_photos_category_landscapes_label,
                photos = persistentListOf(
                    Photo(resId = R.drawable.landscape_0),
                    Photo(resId = R.drawable.landscape_1),
                    Photo(resId = R.drawable.landscape_2),
                    Photo(resId = R.drawable.landscape_3),
                    Photo(resId = R.drawable.landscape_4),
                    Photo(resId = R.drawable.landscape_5),
                    Photo(resId = R.drawable.landscape_6),
                    Photo(resId = R.drawable.landscape_7),
                    Photo(resId = R.drawable.landscape_8),
                    Photo(resId = R.drawable.landscape_9)
                )
            ),
            PhotoCategory(
                label = R.string.examples_photos_category_cats_label,
                photos = persistentListOf(
                    Photo(resId = R.drawable.cat_0),
                    Photo(resId = R.drawable.cat_1),
                    Photo(resId = R.drawable.cat_2),
                    Photo(resId = R.drawable.cat_3),
                    Photo(resId = R.drawable.cat_4),
                    Photo(resId = R.drawable.cat_5),
                    Photo(resId = R.drawable.cat_6),
                    Photo(resId = R.drawable.cat_7),
                    Photo(resId = R.drawable.cat_8),
                    Photo(resId = R.drawable.cat_9)
                )
            ),
            PhotoCategory(
                label = R.string.examples_photos_category_flowers_label,
                photos = persistentListOf(
                    Photo(resId = R.drawable.flower_0),
                    Photo(resId = R.drawable.flower_1),
                    Photo(resId = R.drawable.flower_2),
                    Photo(resId = R.drawable.flower_3),
                    Photo(resId = R.drawable.flower_4),
                    Photo(resId = R.drawable.flower_5),
                    Photo(resId = R.drawable.flower_6),
                    Photo(resId = R.drawable.flower_7),
                    Photo(resId = R.drawable.flower_8),
                    Photo(resId = R.drawable.flower_9)
                )
            )
        )

    }

}