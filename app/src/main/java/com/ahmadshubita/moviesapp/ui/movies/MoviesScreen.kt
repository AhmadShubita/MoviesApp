package com.ahmadshubita.moviesapp.ui.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.ahmadshubita.moviesapp.ui.components.MainListCard
import com.ahmadshubita.moviesapp.ui.core.common.DefaultErrorLayout
import com.ahmadshubita.moviesapp.ui.core.common.DefaultProgressBar
import com.ahmadshubita.moviesapp.ui.movies.components.CustomSwitch
import com.ahmadshubita.moviesapp.ui.movies.components.MoviesMainCard
import com.ahmadshubita.moviesapp.ui.movies.viewmodel.MoviesViewModel
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme
import com.ahmadshubita.moviesapp.ui.theme.dimens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviesScreen(
    navController: NavController, viewModel: MoviesViewModel = hiltViewModel()
) {


    val moviesScreenState by viewModel.state.collectAsState()
    val isDarkTheme = viewModel.isDarkTheme.value

    Scaffold {
        if (!moviesScreenState.isErrorState.value && !moviesScreenState.isLoadingState.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 10.dp, top = 10.dp)
                ) {
                    CategoryTitle(title = "MOVIES", MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.weight(1f))
                    CustomSwitch(
                        checked = isDarkTheme,
                        onCheckedChange = { darkTheme ->
                            viewModel.switchTheme(darkTheme)
                        }
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    LazyHorizontalGrid(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .padding(top = 10.dp, start = 16.dp),
                        rows = GridCells.Fixed(1),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        items(moviesScreenState.topRatedMoviesItems.size) { item ->
                            MoviesMainCard(moviesScreenState.topRatedMoviesItems[item].posterImageUrl,
                                onClick = {})
                        }
                    }
                    CategoryTitle(
                        title = "Now",
                        MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    LazyHorizontalGrid(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .padding(start = dimens.space16), rows = GridCells.Fixed(1)
                    ) {
                        items(moviesScreenState.nowMoviesItems.size) { item ->
                            MainListCard(moviesScreenState.nowMoviesItems[item].title ?: "",
                                moviesScreenState.nowMoviesItems[item].releaseYear,
                                moviesScreenState.nowMoviesItems[item].rating,
                                moviesScreenState.nowMoviesItems[item].posterImageUrl,
                                onClick = {},
                                isWrapContent = false
                            )
                        }
                    }
                    CategoryTitle(
                        title = "Popular",
                        MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    LazyHorizontalGrid(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(480.dp)
                            .padding(start = 10.dp),
                        rows = GridCells.Fixed(2),
                        contentPadding = PaddingValues(6.dp)
                    ) {
                        items(moviesScreenState.popularMoviesItems.size) { item ->
                            MainListCard(moviesScreenState.popularMoviesItems[item].title ?: "",
                                moviesScreenState.popularMoviesItems[item].releaseYear,
                                moviesScreenState.popularMoviesItems[item].rating,
                                moviesScreenState.popularMoviesItems[item].posterImageUrl,
                                onClick = {},
                                isWrapContent = false
                            )
                        }
                    }
                }
            }
        } else if (moviesScreenState.isLoadingState.value) {
            DefaultProgressBar()
        } else {
            DefaultErrorLayout()
        }
    }
}

@Composable
fun CategoryTitle(
    title: String,
    textSize: androidx.compose.ui.text.TextStyle,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    Text(
        text = title,
        textAlign = TextAlign.Start,
        style = textSize,
        modifier = Modifier.padding(16.dp),
        color = color
    )
}

@Preview
@Composable
private fun CategoryTitlePreview() {
    MoviesAppTheme {
        CategoryTitle("Movies", MaterialTheme.typography.titleLarge)
    }
}

@Preview
@Composable
private fun MoviesScreenPreview() {
    MoviesAppTheme {
        MoviesScreen(navController = rememberNavController())
    }
}