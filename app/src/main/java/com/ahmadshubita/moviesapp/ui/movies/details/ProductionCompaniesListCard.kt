package com.ahmadshubita.moviesapp.ui.movies.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme
import com.ahmadshubita.moviesapp.ui.theme.customColors
import com.ahmadshubita.moviesapp.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductionCompaniesListCard(
    title: String,
    year: String,
    path: String,
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
        Card(modifier = Modifier.size(width = 150.dp, height = 240.dp),
            shape = RoundedCornerShape(dimens.space6),
            elevation = CardDefaults.cardElevation(dimens.space10),
            onClick = { onClick() }) {
            Box {
                AsyncImage(
                    path,
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxSize()
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
        ProductionCompaniesListCard(title = "Marvel",
            year = "2022",
            path = "https://egyptianstreets.com/wp-content/uploads/2022/10/GettyImages-1243921482.v1.jpg",
            onClick = {}
        )
    }
}

