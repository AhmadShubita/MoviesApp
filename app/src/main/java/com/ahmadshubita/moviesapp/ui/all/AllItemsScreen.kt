package com.ahmadshubita.moviesapp.ui.all

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahmadshubita.moviesapp.R
import com.ahmadshubita.moviesapp.ui.all.viewmodel.AllItemsViewModel
import com.ahmadshubita.moviesapp.ui.components.MainListCard
import com.ahmadshubita.moviesapp.ui.core.common.DefaultErrorLayout
import com.ahmadshubita.moviesapp.ui.core.common.DefaultProgressBar
import com.ahmadshubita.moviesapp.ui.movies.CategoryTitle
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme
import com.ahmadshubita.moviesapp.ui.theme.dimens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllItemsScreen(
        navController: NavController, viewModel: AllItemsViewModel = hiltViewModel()
) {

    val allItemsScreenState by viewModel.uiState.collectAsState()
    val moviesItems = allItemsScreenState.moviesItems.collectAsLazyPagingItems()

    Scaffold(modifier =
    Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
        if (!allItemsScreenState.isErrorState.value && !allItemsScreenState.isLoadingState.value) {
            Column(Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Row(
                        modifier = Modifier
                                .padding(top = 16.dp, bottom = 20.dp, start = 16.dp, end = 16.dp)
                                .background(MaterialTheme.colorScheme.surface),
                        verticalAlignment = Alignment.CenterVertically,
                ) {
                    CategoryTitle(title = stringResource(id = R.string.all_items_capital), MaterialTheme.typography.titleLarge)
                }
                Spacer(modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background))
                key(moviesItems.loadState) {
                    when (moviesItems.loadState.refresh) {
                        is LoadState.Error -> {
                            DefaultErrorLayout()
                        }

                        is LoadState.Loading -> {
                            DefaultProgressBar()
                        }

                        else -> {}
                    }
                }
                LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(start = dimens.space16)
                ) {
                    items(moviesItems.itemCount) { item ->
                        MainListCard(
                                title = moviesItems[item]?.title ?: "",
                                year = moviesItems[item]?.releaseYear ?: "",
                                rating = moviesItems[item]?.rating ?: "",
                                path = moviesItems[item]?.posterImageUrl ?: "",
                                isWrapContent = true,
                                onClick = {})
                    }

                    when (moviesItems.loadState.append) {
                        is LoadState.Loading -> {
                            item {}
                        }

                        is LoadState.Error -> {
                            item {}
                        }

                        else -> {}
                    }
                }
            }
        } else if (allItemsScreenState.isLoadingState.value) {
            DefaultProgressBar()
        } else {
            DefaultErrorLayout()
        }
    }
}

@Preview
@Composable
private fun MoviesScreenPreview() {
    MoviesAppTheme {
        AllItemsScreen(navController = rememberNavController())
    }
}