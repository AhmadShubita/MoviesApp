package com.ahmadshubita.moviesapp.ui.all.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import androidx.paging.filter
import com.ahmadshubita.moviesapp.base.BaseViewModel
import com.ahmadshubita.moviesapp.data.models.Movie
import com.ahmadshubita.moviesapp.data.remote.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AllItemsViewModel @Inject constructor(
        private val mainRepository: MainRepository,
        savedStateHandle: SavedStateHandle
) : BaseViewModel<AllItemsScreenState, AllItemsUiEffect>(AllItemsScreenState()) {

    private val allItemsScreenArgs = AllItemsScreenArgs(savedStateHandle)

    init {
        onRefreshData()
    }

    private fun getMovies() {
        val isPopularItemsScreen = allItemsScreenArgs.isPopularItemsScreen
        tryToExecutePaging(
                call = {
                    if (isPopularItemsScreen) {
                        mainRepository.getPopularMoviesPaging(LANGUAGE_TYPE, 1)
                    } else {
                        mainRepository.getNowPlayingMoviesPaging(LANGUAGE_TYPE, 1)
                    }
                },
                onSuccess = { response ->
                    onGetMovies(response)
                },
                onError = { onError() }
        )
    }

    private fun onGetMovies(peopleList: PagingData<Movie>) {
        val filteredList = peopleList.filter { !it.posterPath.isNullOrBlank() }
        _uiState.update {
            it.copy(
                    isLoadingState = mutableStateOf(false),
                    isErrorState = mutableStateOf(false),
                    moviesItems = flowOf(filteredList),
            )
        }
    }

    private fun onError() {
        _uiState.update { it.copy(isLoadingState = mutableStateOf(false), isErrorState = mutableStateOf(true)) }
    }

    fun onRefreshData() {
        getMovies()
    }

    fun onClickBackButton(){
        triggerUiEffect(AllItemsUiEffect.NavigateBack)
    }

    companion object {
        const val LANGUAGE_TYPE = "en-US"
    }
}