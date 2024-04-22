package com.ahmadshubita.moviesapp.ui.movies.details.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import com.ahmadshubita.moviesapp.data.models.Movie
import com.ahmadshubita.moviesapp.data.models.People
import com.ahmadshubita.moviesapp.ui.movies.details.model.DetailsItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class DetailsScreenState(
        val isLoadingState: MutableState<Boolean> = mutableStateOf(true),
        val isErrorState: MutableState<Boolean> = mutableStateOf(false),
        val detailsItem: DetailsItem = DetailsItem()
)
