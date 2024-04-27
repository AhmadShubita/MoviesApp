package com.ahmadshubita.moviesapp.ui.movies.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadshubita.moviesapp.R
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun RatingLayout(modifier: Modifier, rating: Double) {
    val value = rating / 2
    Row(modifier = modifier) {

        Image(
            modifier = Modifier.size(20.dp), imageVector = if (value > 1) {
                ImageVector.vectorResource(id = R.drawable.ic_star_filled)
            } else {
                ImageVector.vectorResource(id = R.drawable.ic_star)
            }, contentDescription = ""
        )
        Spacer(modifier = Modifier.size(6.dp))
        Image(
            modifier = Modifier.size(20.dp), imageVector = if (value > 1.5) {
                ImageVector.vectorResource(id = R.drawable.ic_star_filled)
            } else {
                ImageVector.vectorResource(id = R.drawable.ic_star)
            }, contentDescription = ""
        )
        Spacer(modifier = Modifier.size(6.dp))
        Image(
            modifier = Modifier.size(20.dp), imageVector = if (value > 2.5) {
                ImageVector.vectorResource(id = R.drawable.ic_star_filled)
            } else {
                ImageVector.vectorResource(id = R.drawable.ic_star)
            }, contentDescription = ""
        )
        Spacer(modifier = Modifier.size(6.dp))
        Image(
            modifier = Modifier.size(20.dp), imageVector = if (value > 3.5) {
                ImageVector.vectorResource(id = R.drawable.ic_star_filled)
            } else {
                ImageVector.vectorResource(id = R.drawable.ic_star)
            }, contentDescription = ""
        )
        Spacer(modifier = Modifier.size(6.dp))
        Image(
            modifier = Modifier.size(20.dp), imageVector = if (value > 4.5) {
                ImageVector.vectorResource(id = R.drawable.ic_star_filled)
            } else {
                ImageVector.vectorResource(id = R.drawable.ic_star)
            }, contentDescription = ""
        )

    }
}

@Preview
@Composable
fun RatingLayoutPreview() {
    MoviesAppTheme {
        RatingLayout(modifier = Modifier, rating = 9.0)
    }
}