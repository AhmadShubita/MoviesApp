package com.ahmadshubita.moviesapp.ui.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahmadshubita.moviesapp.R
import com.ahmadshubita.moviesapp.ui.bottombar.DetailsType
import com.ahmadshubita.moviesapp.ui.bottombar.navigateAllItemsScreen
import com.ahmadshubita.moviesapp.ui.bottombar.navigateToDetailsScreen
import com.ahmadshubita.moviesapp.ui.components.MainListCard
import com.ahmadshubita.moviesapp.ui.core.common.DefaultErrorLayout
import com.ahmadshubita.moviesapp.ui.core.common.DefaultProgressBar
import com.ahmadshubita.moviesapp.ui.movies.components.CustomSwitch
import com.ahmadshubita.moviesapp.ui.movies.components.MoviesMainCard
import com.ahmadshubita.moviesapp.ui.movies.viewmodel.MoviesScreenState
import com.ahmadshubita.moviesapp.ui.movies.viewmodel.MoviesUiEffect
import com.ahmadshubita.moviesapp.ui.movies.viewmodel.MoviesViewModel
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme
import com.ahmadshubita.moviesapp.ui.theme.dimens
import com.ahmadshubita.moviesapp.ui.util.HandleUiEffect
import com.ahmadshubita.moviesapp.ui.util.SnackBarBuilder
import com.ahmadshubita.moviesapp.ui.util.SnackBarStatus


@Composable
fun MoviesScreen(
        navController: NavController, viewModel: MoviesViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    HandleUiEffect(effect = viewModel.uiEffect) {
        when (it) {
            is MoviesUiEffect.NavigateToAllItems -> {
                navController.navigateAllItemsScreen(
                        it.isPopular
                )
            }

            is MoviesUiEffect.NavigateToDetails -> {
                navController.navigateToDetailsScreen(
                        it.detailsType,
                        it.id
                )
            }
        }
    }
    MoviesContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviesContent(
        moviesScreenState: MoviesScreenState, viewModel: MoviesViewModel
) {

    val coroutineScope = rememberCoroutineScope()

    /**
     * This code is used to initialize the SnackBarBuilder and all its properties
     */
    val snackBarBuilder = SnackBarBuilder()
    snackBarBuilder.snackBarHostState = remember { SnackbarHostState() }
    snackBarBuilder.ConnectivityAwareSnackBar()


    val isDarkTheme = viewModel.isDarkTheme.value

    Scaffold(snackbarHost = { snackBarBuilder.SnackBarHost() }, containerColor = MaterialTheme.colorScheme.surface) {
        if (!moviesScreenState.isErrorState.value && !moviesScreenState.isLoadingState.value) {
            Column(
                    modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surface)
            ) {

                Row(
                        modifier = Modifier
                                .padding(16.dp)
                                .background(MaterialTheme.colorScheme.surface),
                        verticalAlignment = Alignment.CenterVertically,
                ) {
                    CategoryTitle(title = stringResource(id = R.string.movies), MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.weight(1f))
                    CustomSwitch(checked = isDarkTheme, onCheckedChange = { darkTheme ->
                        viewModel.switchTheme(darkTheme)
                    })
                }
                Column(
                        modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
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
                            MoviesMainCard(moviesScreenState.topRatedMoviesItems[item].posterImageUrl
                            ) {
                                viewModel.onMovieItemClick(
                                        DetailsType.MOVIE_DETAILS,
                                        moviesScreenState.topRatedMoviesItems[item].id
                                )
                            }
                        }
                    }
                    Row(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CategoryTitle(
                                title = stringResource(id = R.string.upcoming),
                                MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        TextButton(modifier = Modifier.wrapContentWidth(),
                                onClick = { viewModel.navigateAllItemsScreen(false) }) {
                            Text(
                                    text = stringResource(id = R.string.view_all),
                                    textAlign = TextAlign.Start,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    LazyHorizontalGrid(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .height(240.dp)
                                    .padding(start = dimens.space16), rows = GridCells.Fixed(1)
                    ) {
                        items(moviesScreenState.nowMoviesItems.size) { item ->
                            MainListCard(
                                    moviesScreenState.nowMoviesItems[item].title ?: "",
                                    moviesScreenState.nowMoviesItems[item].releaseYear,
                                    moviesScreenState.nowMoviesItems[item].rating,
                                    moviesScreenState.nowMoviesItems[item].posterImageUrl,
                                    onClick = {
                                        viewModel.onMovieItemClick(
                                                DetailsType.MOVIE_DETAILS,
                                                moviesScreenState.nowMoviesItems[item].id
                                        )
                                    },
                                    isWrapContent = false
                            )
                        }
                    }
                    Row(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = dimens.space16),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CategoryTitle(
                                title = stringResource(id = R.string.popular),
                                MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        TextButton(modifier = Modifier.wrapContentWidth(),
                                onClick = { viewModel.navigateAllItemsScreen(true) }) {
                            Text(
                                    text = stringResource(id = R.string.view_all),
                                    textAlign = TextAlign.Start,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    LazyHorizontalGrid(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .height(480.dp)
                                    .padding(start = dimens.space10),
                            rows = GridCells.Fixed(2),
                            contentPadding = PaddingValues(dimens.space6)
                    ) {
                        items(moviesScreenState.popularMoviesItems.size) { item ->
                            MainListCard(
                                    moviesScreenState.popularMoviesItems[item].title ?: "",
                                    moviesScreenState.popularMoviesItems[item].releaseYear,
                                    moviesScreenState.popularMoviesItems[item].rating,
                                    moviesScreenState.popularMoviesItems[item].posterImageUrl,
                                    onClick = {
                                        viewModel.onMovieItemClick(
                                                DetailsType.MOVIE_DETAILS,
                                                moviesScreenState.popularMoviesItems[item].id
                                        )
                                    },
                                    isWrapContent = false
                            )
                        }
                    }
                }
            }
        } else if (moviesScreenState.isLoadingState.value) {
            DefaultProgressBar()
        } else {
            DefaultErrorLayout{
                viewModel.onRefreshData()
            }
            snackBarBuilder.showSnackBar(
                    coroutineScope = coroutineScope,
                    status = SnackBarStatus.ERROR,
                    message = stringResource(id = R.string.something_went_wrong),
                    throwable = null,
                    actionLabel = stringResource(id = R.string.retry)
            ) {
                viewModel.onRefreshData()
            }
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
            text = title, textAlign = TextAlign.Start, style = textSize, color = color
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