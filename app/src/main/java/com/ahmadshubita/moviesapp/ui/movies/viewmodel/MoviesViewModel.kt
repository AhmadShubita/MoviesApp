package com.ahmadshubita.moviesapp.ui.movies.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.ahmadshubita.moviesapp.BaseViewModel
import com.ahmadshubita.moviesapp.data.remote.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel<MoviesScreenState>(
        MoviesScreenState()
    ) {

    private val _moviesScreenState = MutableStateFlow(MoviesScreenState())
    val moviesScreenState: StateFlow<MoviesScreenState> = _moviesScreenState.asStateFlow()
    init {
        getAll()
    }

     fun getAll() {
        tryToExecute(
            call = {mainRepository.getTopRatedMovies("", 1)},
            onSuccess = { response ->
                _moviesScreenState.update {
                    it.copy(
                        isLoadingState = mutableStateOf(false),
                        isErrorState = mutableStateOf(false) ,
                        mainMoviesItems = response.results
                    )
                }
            },
            onError = {  }
        )
    }
}