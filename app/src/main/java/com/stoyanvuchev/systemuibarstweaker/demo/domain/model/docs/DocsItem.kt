package com.stoyanvuchev.systemuibarstweaker.demo.domain.model.docs

import androidx.compose.runtime.Stable
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.docs.DocsScreenDestinations
import kotlinx.collections.immutable.persistentListOf

@Stable
data class DocsItem(
    val title: String,
    val description: String,
    val readmeRoute: String,
    val readmeText: String
) {

    companion object {

        val docsList
            get() = persistentListOf(
                DocsItem(
                    title = "What is this all about?",
                    description = "A brief introduction to System UI Bars Tweaker, including the story of it's existence.",
                    readmeRoute = DocsScreenDestinations.Introduction.route,
                    readmeText = ""
                ),
                DocsItem(
                    title = "How to implement it?",
                    description = "A full documentation of how to implement the System UI Bars Tweaker into your app.",
                    readmeRoute = "",
                    readmeText = ""
                ),
                DocsItem(
                    title = "When & how to use it?",
                    description = "A full guide with examples of when & how to use the System UI Bars Tweaker in your app.",
                    readmeRoute = "",
                    readmeText = ""
                ),
                DocsItem(
                    title = "What are the best practices?",
                    description = "A detailed guide with the best practices for using the System UI Bars Tweaker.",
                    readmeRoute = "",
                    readmeText = ""
                )
            )

    }

}