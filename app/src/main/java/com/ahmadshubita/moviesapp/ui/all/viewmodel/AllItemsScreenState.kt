package com.ahmadshubita.moviesapp.ui.all.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import com.ahmadshubita.moviesapp.data.models.Movie
import com.ahmadshubita.moviesapp.data.models.People
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class AllItemsScreenState(
        val isLoadingState: MutableState<Boolean> = mutableStateOf(true),
        val isErrorState: MutableState<Boolean> = mutableStateOf(false),
        val isDarkThemeEnabled: MutableState<Boolean> = mutableStateOf(false),
        val moviesItems: Flow<PagingData<Movie>> = flow { }
)
