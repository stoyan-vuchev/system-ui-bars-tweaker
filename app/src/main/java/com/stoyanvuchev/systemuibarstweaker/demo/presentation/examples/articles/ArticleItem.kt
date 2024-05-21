package com.stoyanvuchev.systemuibarstweaker.demo.presentation.examples.articles

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.stoyanvuchev.systemuibarstweaker.demo.R
import com.stoyanvuchev.systemuibarstweaker.demo.domain.model.examples.articles.Article

/**
 * A composable to display an [Article] for the [ArticlesExampleScreen].
 *
 * @param article the article to be displayed.
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleItem(article: Article) {

    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        onClick = remember { { /* Do nothing for now. */ } },
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = article.image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            ElevatedCard(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd),
                shape = RoundedCornerShape(100),
                content = {

                    Text(
                        modifier = Modifier.padding(horizontal = 11.dp, vertical = 5.dp),
                        text = article.date,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                }
            )

        }

        Divider(color = MaterialTheme.colorScheme.outline)

        Column(modifier = Modifier.padding(start = 16.dp)) {

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = article.title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.padding(end = 16.dp),
                verticalAlignment = Alignment.Bottom
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                ) {

                    Text(
                        text = article.description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = RoundedCornerShape(100)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        text = article.source,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                }

                var isBookmarked by remember { mutableStateOf(false) }
                val bookmarkButtonColors by rememberUpdatedState(
                    if (isBookmarked) {
                        ButtonDefaults.elevatedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    } else ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )

                ElevatedButton(
                    onClick = remember { { isBookmarked = !isBookmarked } },
                    colors = bookmarkButtonColors,
                    content = {

                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(
                                id = if (isBookmarked) R.drawable.bookmark_filled
                                else R.drawable.bookmark
                            ),
                            contentDescription = "Bookmark"
                        )

                    }
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

        }

    }

}