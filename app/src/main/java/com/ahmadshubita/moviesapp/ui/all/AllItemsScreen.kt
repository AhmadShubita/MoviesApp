package com.ahmadshubita.moviesapp.ui.all

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahmadshubita.moviesapp.R
import com.ahmadshubita.moviesapp.ui.all.viewmodel.AllItemsScreenState
import com.ahmadshubita.moviesapp.ui.all.viewmodel.AllItemsUiEffect
import com.ahmadshubita.moviesapp.ui.all.viewmodel.AllItemsViewModel
import com.ahmadshubita.moviesapp.ui.components.MainListCard
import com.ahmadshubita.moviesapp.ui.core.common.DefaultErrorLayout
import com.ahmadshubita.moviesapp.ui.core.common.DefaultProgressBar
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme
import com.ahmadshubita.moviesapp.ui.theme.dimens
import com.ahmadshubita.moviesapp.ui.util.HandleUiEffect


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllItemsScreen(
    navController: NavController, viewModel: AllItemsViewModel = hiltViewModel()
) {

    val allItemsScreenState by viewModel.uiState.collectAsState()
    HandleUiEffect(effect = viewModel.uiEffect) { viewAllViewModel ->
        when (viewAllViewModel) {
            is AllItemsUiEffect.NavigateBack -> {
                navController.popBackStack()
            }

            is AllItemsUiEffect.NavigateToDetails -> {
            }
        }
    }
    AllItemsContent(allItemsScreenState, viewModel)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllItemsContent(
    state: AllItemsScreenState, viewModel: AllItemsViewModel = hiltViewModel()
) {
    val moviesItems = state.moviesItems.collectAsLazyPagingItems()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(modifier = Modifier
                .wrapContentSize()
                .shadow(4.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.all_items_capital),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onClickBackButton()}) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                })
        }) {
        if (!state.isErrorState.value && !state.isLoadingState.value) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(MaterialTheme.colorScheme.surface)
            ) {

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                )
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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(start = dimens.space16)
                ) {
                    items(moviesItems.itemCount) { item ->
                        MainListCard(title = moviesItems[item]?.title ?: "",
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
        } else if (state.isLoadingState.value) {
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