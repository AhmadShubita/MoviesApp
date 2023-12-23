package com.ahmadshubita.moviesapp.ui.movies.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ahmadshubita.moviesapp.data.models.Movie

data class MoviesScreenState (
    val isLoadingState: MutableState<Boolean> = mutableStateOf(false),
    val isErrorState: MutableState<Boolean> = mutableStateOf(false),
    val isDarkThemeEnabled: MutableState<Boolean> = mutableStateOf(false),
    val mainMoviesItems: List<Movie> =emptyList()
)
