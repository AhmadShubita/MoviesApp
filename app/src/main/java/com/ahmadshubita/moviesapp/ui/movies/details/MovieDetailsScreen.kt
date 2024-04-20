package com.ahmadshubita.moviesapp.ui.movies.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahmadshubita.moviesapp.R
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme
import com.ahmadshubita.moviesapp.ui.theme.customColors
import com.ahmadshubita.moviesapp.ui.theme.dimens

@Composable
fun MovieDetailsScreen(
    navController: NavController
) {
    MovieDetailsScreen()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen() {

    val colors = customColors
    val dimens = dimens
    val fontStyle = MaterialTheme.typography
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Box(modifier = Modifier.height(400.dp)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimens.space10)
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            modifier = Modifier.clickable { },
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
                AsyncImage(
                    "path",
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
                                    colors.transparent.copy(alpha = dimens.floatValues.float0_8)
                                ), startY = dimens.floatValues.float200
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .wrapContentHeight()
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = dimens.space20),
                        text = "Fast Farient",
                        color = MaterialTheme.colorScheme.onTertiary,
                        style = fontStyle.titleLarge,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(
                                start = dimens.space20,
                                end = dimens.space20,
                                bottom = dimens.space20
                            ), verticalAlignment = Alignment.Bottom
                    ) {
                        Column(
                            Modifier.weight(1f)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "322 playing now",
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                style = fontStyle.labelSmall,
                                maxLines = 5,
                                overflow = TextOverflow.Ellipsis
                            )

                            Row(
                                modifier = Modifier.padding(top = 12.dp),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = "9.7",
                                    color = MaterialTheme.colorScheme.secondary,
                                    style = fontStyle.titleLarge,
                                    maxLines = 5,
                                    overflow = TextOverflow.Ellipsis
                                )
                                RatingLayout(
                                    modifier = Modifier.padding(start = 8.dp, bottom = 2.dp),
                                    rating = 9.0
                                )
                            }
                        }
                        Card(
                            modifier = Modifier
                                .size(dimens.space64)
                                .weight(0.24f)
                                .padding(top = dimens.space8, end = dimens.space8)
                                .clickable { },
                            shape = RoundedCornerShape(dimens.space56),
                            elevation = CardDefaults.cardElevation(dimens.space16),
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
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_play),
                                    modifier = Modifier
                                        .size(18.dp)
                                        .padding(start = 2.dp)
                                        .align(
                                            Alignment.Center
                                        ),
                                    tint = Color.White,
                                    contentDescription = ""
                                )
                            }
                        }

                    }
                    Text(
                        modifier = Modifier
                            .padding(bottom = dimens.space40)
                            .padding(horizontal = dimens.space20),
                        text = "This Title will be like this is not acceptable",
                        color = MaterialTheme.colorScheme.onTertiary,
                        style = fontStyle.titleLarge,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Text(
                text = "Production Companies",
                modifier = Modifier.padding(start = dimens.space16, top = dimens.space24),
                style = fontStyle.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            LazyHorizontalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(start = dimens.space16, top = dimens.space20),
                rows = GridCells.Fixed(1)
            ) {
                items(6) { item ->
                    ProductionCompaniesListCard(
                        "", "", "", onClick = {}
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(
                        start = dimens.space14, end = dimens.space16, top = dimens.space26
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(70.dp)
                        .clickable { },
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_like_filled),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.size(20.dp))
                Image(
                    modifier = Modifier
                        .size(70.dp)
                        .clickable { },
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_star_circle_filled),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.size(20.dp))
                Image(
                    modifier = Modifier
                        .size(70.dp)
                        .clickable { },
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_comment_filled),
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MoviesAppTheme {
        MovieDetailsScreen()
    }
}