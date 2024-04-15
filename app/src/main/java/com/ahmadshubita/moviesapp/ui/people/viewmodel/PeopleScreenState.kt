package com.ahmadshubita.moviesapp.ui.people.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ahmadshubita.moviesapp.data.models.Movie
import com.ahmadshubita.moviesapp.data.models.People

data class PeopleScreenState (
    val isLoadingState: MutableState<Boolean> = mutableStateOf(true),
    val isErrorState: MutableState<Boolean> = mutableStateOf(false),
    val isDarkThemeEnabled: MutableState<Boolean> = mutableStateOf(false),
    val peopleItems: List<People> =emptyList(),
)
