package com.ahmadshubita.moviesapp.ui.tv

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahmadshubita.moviesapp.R
import com.ahmadshubita.moviesapp.ui.bottombar.DetailsType
import com.ahmadshubita.moviesapp.ui.bottombar.navigateToDetailsScreen
import com.ahmadshubita.moviesapp.ui.components.MainListCard
import com.ahmadshubita.moviesapp.ui.core.common.DefaultErrorLayout
import com.ahmadshubita.moviesapp.ui.core.common.DefaultProgressBar
import com.ahmadshubita.moviesapp.ui.movies.CategoryTitle
import com.ahmadshubita.moviesapp.ui.theme.dimens
import com.ahmadshubita.moviesapp.ui.tv.viewmodel.TvScreenState
import com.ahmadshubita.moviesapp.ui.tv.viewmodel.TvUiEffect
import com.ahmadshubita.moviesapp.ui.tv.viewmodel.TvViewModel
import com.ahmadshubita.moviesapp.ui.util.HandleUiEffect
import com.ahmadshubita.moviesapp.ui.util.SnackBarBuilder
import com.ahmadshubita.moviesapp.ui.util.SnackBarStatus

@Composable
fun TvScreen(
        navController: NavController, viewModel: TvViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    HandleUiEffect(effect = viewModel.uiEffect) {
        when (it) {
            is TvUiEffect.NavigateToDetails -> {
                navController.navigateToDetailsScreen(
                        it.detailsType,
                        it.id
                )
            }
        }
    }
    TvContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TvContent(
        tvScreenState: TvScreenState, viewModel: TvViewModel
) {
    val topRatedTvItems = tvScreenState.topRatedTvItems.collectAsLazyPagingItems()

    val coroutineScope = rememberCoroutineScope()

    /**
     * This code is used to initialize the SnackBarBuilder and all its properties
     */
    val snackBarBuilder = SnackBarBuilder()
    snackBarBuilder.snackBarHostState = remember { SnackbarHostState() }
    snackBarBuilder.ConnectivityAwareSnackBar()

    Scaffold(
            snackbarHost = { snackBarBuilder.SnackBarHost() },
            containerColor = MaterialTheme.colorScheme.surface,
    ) {
        if (!tvScreenState.isErrorState.value && !tvScreenState.isLoadingState.value) {
            Column(
                    Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surface)
            ) {
                key(topRatedTvItems.loadState) {
                    when (topRatedTvItems.loadState.refresh) {
                        is LoadState.Error -> {
                            DefaultErrorLayout()
                            snackBarBuilder.showSnackBar(
                                    coroutineScope = coroutineScope,
                                    status = SnackBarStatus.ERROR,
                                    message = stringResource(id = R.string.some_thing_went_wrong),
                                    throwable = null,
                                    actionLabel = stringResource(id = R.string.retry)
                            ) {
                                viewModel.onRefreshData()
                            }
                        }

                        is LoadState.Loading -> {
                            DefaultProgressBar()
                        }

                        else -> {}
                    }
                }
                Row(
                        modifier = Modifier
                                .padding(top = 16.dp, bottom = 20.dp, start = 16.dp, end = 16.dp)
                                .background(MaterialTheme.colorScheme.surface),
                        verticalAlignment = Alignment.CenterVertically,
                ) {
                    CategoryTitle(title = "TV", MaterialTheme.typography.titleLarge)
                }
                Spacer(
                        modifier = Modifier
                                .height(10.dp)
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background)
                )
                LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(start = dimens.space16)
                ) {
                    items(topRatedTvItems.itemCount) { item ->
                        MainListCard(
                                title = topRatedTvItems[item]?.name ?: "",
                                year = topRatedTvItems[item]?.releaseYear ?: "",
                                rating = topRatedTvItems[item]?.rating ?: "",
                                path = topRatedTvItems[item]?.posterImageUrl ?: "",
                                onClick = {
                                    topRatedTvItems[item]?.id?.let { id ->
                                        viewModel.onMovieItemClick(
                                                DetailsType.TV_DETAILS,
                                                id
                                        )
                                    }
                                }, isWrapContent = true
                        )
                    }

                    when (topRatedTvItems.loadState.append) {
                        is LoadState.Loading -> {
                            item {
                                DefaultProgressBar()
                            }
                        }

                        is LoadState.Error -> {
                            // TODO Add error item with retry button
                        }

                        else -> {}
                    }
                }
            }
        } else if (tvScreenState.isLoadingState.value) {
            DefaultProgressBar()
        } else {
            DefaultErrorLayout()
            snackBarBuilder.showSnackBar(
                    coroutineScope = coroutineScope,
                    status = SnackBarStatus.ERROR,
                    message = stringResource(id = R.string.some_thing_went_wrong),
                    throwable = null,
                    actionLabel = stringResource(id = R.string.retry)
            ) {
                viewModel.onRefreshData()
            }
        }
    }
}