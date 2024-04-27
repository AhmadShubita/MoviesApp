package com.ahmadshubita.moviesapp.ui.tv.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import com.ahmadshubita.moviesapp.data.models.Tv
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class TvScreenState(
    val isLoadingState: MutableState<Boolean> = mutableStateOf(true),
    val isErrorState: MutableState<Boolean> = mutableStateOf(false),
    val topRatedTvItems: Flow<PagingData<Tv>> = flow { }
)
