package com.ahmadshubita.moviesapp.ui.people

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahmadshubita.moviesapp.ui.components.MainListCard
import com.ahmadshubita.moviesapp.ui.core.common.DefaultErrorLayout
import com.ahmadshubita.moviesapp.ui.core.common.DefaultProgressBar
import com.ahmadshubita.moviesapp.ui.movies.CategoryTitle
import com.ahmadshubita.moviesapp.ui.people.viewmodel.PeopleViewModel
import com.ahmadshubita.moviesapp.ui.theme.dimens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PeopleScreen(
        navController: NavController, viewModel: PeopleViewModel = hiltViewModel()
) {
    val peopleScreenState by viewModel.uiState.collectAsState()
    val peopleItems = peopleScreenState.peopleItems.collectAsLazyPagingItems()


    Scaffold(modifier =
    Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
        if (!peopleScreenState.isErrorState.value && !peopleScreenState.isLoadingState.value) {
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
                    CategoryTitle(title = "PEOPLE", MaterialTheme.typography.titleLarge)
                }
                Spacer(modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background))
                key(peopleItems.loadState) {
                    when (peopleItems.loadState.refresh) {
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
                    items(peopleItems.itemCount) { item ->
                        MainListCard(
                                title = peopleItems[item]?.originalName ?: "",
                                year = "",
                                rating = peopleItems[item]?.rating ?: "",
                                path = peopleItems[item]?.profileImageUrl ?: "",
                                isPeople = true,
                                isWrapContent = true,
                                onClick = {})
                    }

                    when (peopleItems.loadState.append) {
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
        } else if (peopleScreenState.isLoadingState.value) {
            DefaultProgressBar()
        } else {
            DefaultErrorLayout()
        }
    }
}