package com.ahmadshubita.moviesapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.magnifier
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ahmadshubita.moviesapp.R
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme
import com.ahmadshubita.moviesapp.ui.theme.customColors
import com.ahmadshubita.moviesapp.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainListCard(
    title: String,
    year: String,
    rating: String,
    painter: Painter,
    onClick: () -> Unit,
    contentDescription: String = ""
) {
    val colors = customColors
    val fontStyle = MaterialTheme.typography
    val dimens = dimens

    Box(modifier = Modifier.wrapContentSize().padding(bottom = 6.dp, end = 4.dp)) {

        Card(modifier = Modifier.size(width = 140.dp, height = 220.dp),
            shape = RoundedCornerShape(dimens.space8),
            elevation = CardDefaults.cardElevation(dimens.space6),
            onClick = { onClick() }) {
            Box {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.ic_cover),
                    contentScale = ContentScale.Crop,
                    contentDescription = contentDescription
                )
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = contentDescription
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
                Card(
                    modifier = Modifier
                        .size(30.dp)
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
                            text = rating,
                            color = MaterialTheme.colorScheme.onTertiary,
                            style = fontStyle.titleSmall,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
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
                        maxLines = 2,
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
        MainListCard(title = "Marvel",
            year = "2022",
            rating = "9.3",
            painter = rememberAsyncImagePainter(
                model = "https://egyptianstreets.com/wp-content/uploads/2022/10/GettyImages-1243921482.v1.jpg"
            ),
            onClick = {})
    }
}

