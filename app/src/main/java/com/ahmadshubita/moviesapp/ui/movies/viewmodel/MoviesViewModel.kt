package com.ahmadshubita.moviesapp.ui.movies.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.ahmadshubita.moviesapp.BaseViewModel
import com.ahmadshubita.moviesapp.data.local.MainDataStore
import com.ahmadshubita.moviesapp.data.remote.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dataStore: MainDataStore
) : BaseViewModel<MoviesScreenState>(MoviesScreenState()) {

    var isDarkTheme = mutableStateOf(false)

    init {
        viewModelScope.launch {
            dataStore.getDarkThemePrefs().collect {
                isDarkTheme.value = it
            }
        }
        getTopRatedMovies()
    }

    private fun getTopRatedMovies() {
        tryToExecuteConcurrently(call1 = { mainRepository.getTopRatedMovies(LANGUAGE_TYPE, 1) },
            call2 = { mainRepository.getNowPlayingMovies(LANGUAGE_TYPE, 1) },
            call3 = { mainRepository.getPopularMovies(LANGUAGE_TYPE, 1) },
            onSuccess = { r1, r2, r3 ->
                _state.update {
                    it.copy(
                        isLoadingState = mutableStateOf(false),
                        isErrorState = mutableStateOf(false),
                        topRatedMoviesItems = r1.results,
                        nowMoviesItems = r2.results,
                        popularMoviesItems = r3.results
                    )
                }
            },
            onError = { })
    }

    fun switchTheme(newState: Boolean) {
        viewModelScope.launch { dataStore.setDarkThemePrefs(newState) }
    }

    companion object{
        const val LANGUAGE_TYPE = "en-US"
    }
}