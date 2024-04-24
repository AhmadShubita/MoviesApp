package com.ahmadshubita.moviesapp.ui.tv.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import androidx.paging.filter
import com.ahmadshubita.moviesapp.base.BaseViewModel
import com.ahmadshubita.moviesapp.data.models.Tv
import com.ahmadshubita.moviesapp.data.remote.repo.MainRepository
import com.ahmadshubita.moviesapp.ui.bottombar.DetailsType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel<TvScreenState, TvUiEffect>(
        TvScreenState()
    ) {

    init {
        onRefreshData()
    }

    private fun getTvTopRated() {
        executePaging(call = { mainRepository.getTvTopRated(LANGUAGE_TYPE, 1) },
            onSuccess = { response ->
                onGetTvTopRated(response)
            },
            onError = { onError() })
    }

    private fun onGetTvTopRated(tvTopRatedList: PagingData<Tv>) {
        val filteredList = tvTopRatedList.filter { !it.posterPath.isNullOrBlank() }
        uiMutableState.update {
            it.copy(
                isLoadingState = mutableStateOf(false),
                isErrorState = mutableStateOf(false),
                topRatedTvItems = flowOf(filteredList),
            )
        }
    }

    private fun onError() {
        uiMutableState.update {
            it.copy(
                    isLoadingState = mutableStateOf(false),
                    isErrorState = mutableStateOf(true),
            )
        }
    }

    fun onRefreshData(){
        getTvTopRated()
    }

    fun onMovieItemClick(detailsType: DetailsType, id: Int) {
        triggerUiEffect(TvUiEffect.NavigateToDetails(detailsType, id.toString()))
    }

    companion object {
        const val LANGUAGE_TYPE = "en-US"
    }
}