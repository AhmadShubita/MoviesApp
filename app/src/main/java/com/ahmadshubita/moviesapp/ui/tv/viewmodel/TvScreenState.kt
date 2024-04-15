package com.ahmadshubita.moviesapp.ui.tv.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ahmadshubita.moviesapp.data.models.Movie
import com.ahmadshubita.moviesapp.data.models.Tv

data class TvScreenState (
    val isLoadingState: MutableState<Boolean> = mutableStateOf(true),
    val isErrorState: MutableState<Boolean> = mutableStateOf(false),
    val isDarkThemeEnabled: MutableState<Boolean> = mutableStateOf(false),
    val topRatedTvItems: List<Tv> =emptyList()
)
