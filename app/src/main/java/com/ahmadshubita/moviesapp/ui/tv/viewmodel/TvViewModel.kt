package com.ahmadshubita.moviesapp.ui.tv.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import androidx.paging.filter
import com.ahmadshubita.moviesapp.base.BaseUiEffect
import com.ahmadshubita.moviesapp.base.BaseViewModel
import com.ahmadshubita.moviesapp.data.models.Tv
import com.ahmadshubita.moviesapp.data.remote.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(private val mainRepository: MainRepository) :
        BaseViewModel<TvScreenState, BaseUiEffect>(
                TvScreenState()
        ) {

    init {
        onRefreshData()
    }

    private fun getTvTopRated() {
        tryToExecutePaging(
                call = { mainRepository.getTvTopRated(LANGUAGE_TYPE, 1) },
                onSuccess = { response ->
                    onGetTvTopRated(response)
                },
                onError = { onError() }
        )
    }

    private fun onGetTvTopRated(tvTopRatedList: PagingData<Tv>) {
        val filteredList = tvTopRatedList.filter { !it.posterPath.isNullOrBlank() }
        _uiState.update {
            it.copy(
                    isLoadingState = mutableStateOf(false),
                    isErrorState = mutableStateOf(false),
                    topRatedTvItems = flowOf(filteredList),
            )
        }
    }

    private fun onError() {
        _uiState.update { it.copy(isLoadingState = mutableStateOf(false), isErrorState = mutableStateOf(true)) }
    }

    fun onRefreshData() {
        getTvTopRated()
    }

    companion object {
        const val LANGUAGE_TYPE = "en-US"
    }
}