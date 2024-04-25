package com.ahmadshubita.moviesapp.ui.people

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
import com.ahmadshubita.moviesapp.ui.components.MainListCard
import com.ahmadshubita.moviesapp.ui.core.common.DefaultErrorLayout
import com.ahmadshubita.moviesapp.ui.core.common.DefaultProgressBar
import com.ahmadshubita.moviesapp.ui.movies.CategoryTitle
import com.ahmadshubita.moviesapp.ui.people.viewmodel.PeopleViewModel
import com.ahmadshubita.moviesapp.ui.theme.dimens
import com.ahmadshubita.moviesapp.ui.util.SnackBarBuilder
import com.ahmadshubita.moviesapp.ui.util.SnackBarStatus

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PeopleScreen(
        navController: NavController, viewModel: PeopleViewModel = hiltViewModel()
) {
    val peopleScreenState by viewModel.uiState.collectAsState()
    val peopleItems = peopleScreenState.peopleItems.collectAsLazyPagingItems()

    val coroutineScope = rememberCoroutineScope()

    /**
     * This code is used to initialize the SnackBarBuilder and all its properties
     */
    val snackBarBuilder = SnackBarBuilder()
    snackBarBuilder.snackBarHostState = remember { SnackbarHostState() }
    snackBarBuilder.ConnectivityAwareSnackBar()

    val notImplemented = stringResource(id = R.string.this_feature_not_implemented)

    Scaffold(
            snackbarHost = { snackBarBuilder.SnackBarHost() },
            containerColor = MaterialTheme.colorScheme.surface
    ) {
        if (!peopleScreenState.isErrorState.value && !peopleScreenState.isLoadingState.value) {
            Column(Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                key(peopleItems.loadState) {
                    when (peopleItems.loadState.refresh) {
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
                    CategoryTitle(title = "PEOPLE", MaterialTheme.typography.titleLarge)
                }
                Spacer(modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background))
                LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(start = dimens.space16)
                ) {
                    items(peopleItems.itemCount) { item ->
                        MainListCard(
                                title = peopleItems[item]?.originalName ?: "",
                                year = "",
                                rating = peopleItems[item]?.rating ?: "",
                                path = peopleItems[item]?.profileImageUrl ?: "",
                                isPeople = true,
                                isWrapContent = true,
                                onClick = {
                                    snackBarBuilder.showSnackBar(
                                            coroutineScope = coroutineScope,
                                            status = SnackBarStatus.DEFAULT,
                                            message = notImplemented,
                                            throwable = null,
                                    )
                                })
                    }

                    when (peopleItems.loadState.append) {
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
        } else if (peopleScreenState.isLoadingState.value) {
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