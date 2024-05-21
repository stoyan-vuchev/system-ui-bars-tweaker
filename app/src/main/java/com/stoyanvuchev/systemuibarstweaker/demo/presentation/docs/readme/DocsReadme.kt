package com.stoyanvuchev.systemuibarstweaker.demo.presentation.docs.readme

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText
import com.halilibo.richtext.ui.RichTextStyle
import com.halilibo.richtext.ui.material3.SetupMaterial3RichText
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.etc.LocalPaddingValues

@Composable
fun DocsReadme() {
    SetupMaterial3RichText {
        RichText(
            modifier = Modifier
                .padding(LocalPaddingValues.current)
                // .systemBarsPadding()
                .verticalScroll(rememberScrollState()),
            style = RichTextStyle.Default
        ) {
            Markdown(
                """
    # Demo

    ```Kotlin
    val tweaker = rememberSystemUIBarsTweaker()
    
    DisposableEffect(tweaker, darkTheme) {
        tweaker.tweakStatusBarStyle(
            style = SystemBarStyle(
                color = Color.Unspecified,
                darkIcons = !darkTheme,
                enforceContrast = false
            )
        )
        onDispose {}
    }
    ```

    Markdown | Table | Extension
    --- | --- | ---
    *renders* | `beautiful images` | ![random image](https://picsum.photos/seed/picsum/400/400 "Text 1")
    1 | 2 | 3

    > Blockquotes are very handy in email to emulate reply text.
    > This line is part of the same quote.
            """.trimIndent()
            )
        }
    }
}