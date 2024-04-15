package com.ahmadshubita.moviesapp.ui.tv

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahmadshubita.moviesapp.ui.components.MainListCard
import com.ahmadshubita.moviesapp.ui.movies.CategoryTitle
import com.ahmadshubita.moviesapp.ui.theme.dimens
import com.ahmadshubita.moviesapp.ui.tv.viewmodel.TvViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TvScreen(
    navController: NavController, viewModel: TvViewModel = hiltViewModel()
) {
    val tvScreenState by viewModel.state.collectAsState()

    Scaffold(
        modifier =
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (!tvScreenState.isErrorState.value && !tvScreenState.isLoadingState.value) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 10.dp)
            ) {
                CategoryTitle(title = "TV", MaterialTheme.typography.titleLarge)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(start = dimens.space16)
                ) {
                    items(tvScreenState.topRatedTvItems.size) { item ->
                        MainListCard(tvScreenState.topRatedTvItems[item].name ?: "",
                            tvScreenState.topRatedTvItems[item].releaseYear,
                            tvScreenState.topRatedTvItems[item].rating,
                            tvScreenState.topRatedTvItems[item].posterImageUrl,
                            onClick = {}, isWrapContent = true
                        )
                    }
                }
            }
        } else if (tvScreenState.isLoadingState.value) {

        } else {

        }
    }
}