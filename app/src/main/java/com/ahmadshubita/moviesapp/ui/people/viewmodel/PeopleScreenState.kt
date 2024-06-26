package com.ahmadshubita.moviesapp.ui.people.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import com.ahmadshubita.moviesapp.data.models.People
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class PeopleScreenState(
    val isLoadingState: MutableState<Boolean> = mutableStateOf(true),
    val isErrorState: MutableState<Boolean> = mutableStateOf(false),
    val peopleItems: Flow<PagingData<People>> = flow { }
)
