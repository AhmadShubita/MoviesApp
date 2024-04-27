package com.ahmadshubita.moviesapp.ui.movies.components

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme
import com.ahmadshubita.moviesapp.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesMainCard(
    path: String, onClick: () -> Unit
) {
    Card(modifier = Modifier
        .size(width = dimens.space340, height = dimens.space170)
        .padding(end = dimens.space12, top = dimens.space5),
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(dimens.space10),
        onClick = { onClick() }) {
        Box {
            AsyncImage(
                path,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoviesMainCardPreview() {
    MoviesAppTheme {
        MoviesMainCard("") {}
    }
}

