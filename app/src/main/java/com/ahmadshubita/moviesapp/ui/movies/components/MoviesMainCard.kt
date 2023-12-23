package com.ahmadshubita.moviesapp.ui.movies.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ahmadshubita.moviesapp.R
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesMainCard(
    painter: Painter, onClick: () -> Unit, contentDescription: String = ""
) {
    Card(modifier = Modifier
        .size(width = 340.dp, height = 170.dp)
        .padding(end=5.dp ,top= 5.dp, bottom = 5.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = { onClick() }
    ) {
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
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoviesMainCardPreview() {
    MoviesAppTheme {
        MoviesMainCard(rememberAsyncImagePainter(
            model = "https://media.istockphoto.com/id/1336937059/photo/film-reels-on-black-background-movie-video-and-cinema-prodaction-and-edition-concept.jpg?s=1024x1024&w=is&k=20&c=yj2Xt_I0mfvQvWjaPx9Nqy340vL8Lc6A404RynGWvUI="
        ), {})
    }
}

