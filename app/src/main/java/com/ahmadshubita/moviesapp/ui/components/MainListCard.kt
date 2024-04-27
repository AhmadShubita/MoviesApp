package com.ahmadshubita.moviesapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme
import com.ahmadshubita.moviesapp.ui.theme.customColors
import com.ahmadshubita.moviesapp.ui.theme.dimens

@Composable
fun MainListCard(
    title: String,
    year: String,
    rating: String,
    path: String,
    isPeople: Boolean = false,
    isWrapContent: Boolean,
    onClick: () -> Unit,
) {
    val colors = customColors
    val fontStyle = MaterialTheme.typography
    val dimens = dimens

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 16.dp, end = 16.dp)
    ) {
        val modifier = if (isWrapContent) {
            Modifier
                .fillMaxWidth()
                .height(220.dp)
        } else {
            Modifier.size(width = 150.dp, height = 240.dp)
        }
        Card(modifier = modifier,
            shape = RoundedCornerShape(dimens.space6),
            elevation = CardDefaults.cardElevation(dimens.space10),
            onClick = { onClick() }) {
            Box {
                AsyncImage(
                    path,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    colors.transparent,
                                    colors.transparent.copy(alpha = dimens.floatValues.float0_9)
                                ), startY = dimens.floatValues.float200
                            )
                        )
                )
                if (!isPeople) {
                    Card(
                        modifier = Modifier
                            .size(35.dp)
                            .align(Alignment.TopEnd)
                            .padding(top = dimens.space8, end = dimens.space8),
                        shape = RoundedCornerShape(dimens.space16),
                        elevation = CardDefaults.cardElevation(dimens.space6),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            MaterialTheme.colorScheme.onPrimaryContainer,
                                            MaterialTheme.colorScheme.primaryContainer
                                        )
                                    )
                                ), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(top = 2.dp),
                                text = rating.format("0.0"),
                                color = MaterialTheme.colorScheme.onTertiary,
                                style = fontStyle.titleSmall,
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
                Column(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = dimens.space2)
                            .padding(horizontal = dimens.space10),
                        text = year,
                        color = MaterialTheme.colorScheme.onTertiary,
                        style = fontStyle.labelSmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        modifier = Modifier
                            .padding(bottom = dimens.space6)
                            .padding(horizontal = dimens.space10),
                        text = title,
                        color = MaterialTheme.colorScheme.onTertiary,
                        style = fontStyle.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun CardPreview() {
    MoviesAppTheme {
        MainListCard(
            title = "Marvel",
            year = "2022",
            rating = "9.3",
            path = "https://egyptianstreets.com/wp-content/uploads/2022/10/GettyImages-1243921482.v1.jpg",
            onClick = {},
            isWrapContent = false
        )
    }
}

