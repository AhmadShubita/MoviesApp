package com.ahmadshubita.moviesapp.ui.movies.details.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ahmadshubita.moviesapp.ui.movies.details.model.DetailsItem

data class DetailsScreenState(
    val isLoadingState: MutableState<Boolean> = mutableStateOf(true),
    val isErrorState: MutableState<Boolean> = mutableStateOf(false),
    val detailsItem: DetailsItem = DetailsItem()
)
