package com.stoyanvuchev.systemuibarstweaker.demo.domain.model.examples.articles

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.stoyanvuchev.systemuibarstweaker.demo.R

/**
 * A data class containing the necessary properties for an [ArticleItem].
 *
 * @property id the ID of the article.
 * @property source the source of the article.
 * @property url the URL address of the article.
 * @property date the date when the article was written.
 * @property title the title/headline of the article.
 * @property description a short description of the article.
 * @property image a reference to the image resource of the article headline.
 **/
@Stable
data class Article(
    val id: Long,
    val source: String,
    val url: String,
    val date: String,
    val title: String,
    val description: String,
    @DrawableRes val image: Int
) {

    companion object {

        /**
         * A list containing some articles for the demonstration.
         **/
        val articlesList: List<Article> = listOf(
            Article(
                id = 0,
                source = "Rob Thubron on TechSpot",
                url = "https://www.techspot.com/news/100048-google-gives-android-makeover-updated-logo-alternative-bugdroids.html",
                date = "Sep 6, 2023",
                title = "Google gives Android a makeover with updated logo and alternative bug-droids",
                description = "The mascot has some interesting new looks",
                image = R.drawable.article_img_1
            ),
            Article(
                id = 1,
                source = "Ben Trengrove on Medium",
                url = "https://medium.com/androiddevelopers/an-update-on-jetpack-compose-accompanist-libraries-august-2023-ac4cbbf059f1",
                date = "Aug 24, 2023",
                title = "An update on Jetpack Compose Accompanist libraries — August 2023",
                description = "Accompanist will be deprecating the following libraries, with no replacement",
                image = R.drawable.article_img_2
            ),
            Article(
                id = 2,
                source = "Dave Burke on Google Blog",
                url = "https://blog.google/products/android/new-android-features-generative-ai/",
                date = "May 10, 2023",
                title = "Express yourself on Android, with help from AI",
                description = "From the car we drive to the clothes we wear, the products we use every day help us express ourselves. That same self-expression and customization is core to the Android experience, spanning billions of active devices around the world.",
                image = R.drawable.article_img_3
            )
        )

    }

}