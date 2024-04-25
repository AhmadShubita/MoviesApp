package com.ahmadshubita.moviesapp.ui.movies.details.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.ahmadshubita.moviesapp.base.BaseViewModel
import com.ahmadshubita.moviesapp.data.remote.repo.MainRepository
import com.ahmadshubita.moviesapp.ui.bottombar.DetailsType
import com.ahmadshubita.moviesapp.ui.movies.details.model.DetailsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailsScreenState, DetailsUiEffect>(DetailsScreenState()) {

    private val detailsScreenArgs = DetailsScreenArgs(savedStateHandle)

    init {
        getDetails()
    }

    private fun getDetails() {
        val detailsType = detailsScreenArgs.detailsType
        execute(
            call = {
                if (detailsType == DetailsType.TV_DETAILS) {
                    mainRepository.getTvSeriesById(detailsScreenArgs.id.trim().toInt())
                } else {
                    mainRepository.getMovieById(detailsScreenArgs.id.trim().toInt())
                }
            },
            onSuccess = { response ->
                onGetDetails(response)
            },
            onError = {
                onError()
            }
        )
    }

    private fun onGetDetails(detailsItem: DetailsItem) {
        uiMutableState.update {
            it.copy(
                isLoadingState = mutableStateOf(false),
                isErrorState = mutableStateOf(false),
                detailsItem =  detailsItem
            )
        }
    }

    private fun onError() {
        uiMutableState.update {
            it.copy(
                isLoadingState = mutableStateOf(false),
                isErrorState = mutableStateOf(true)
            )
        }
    }

    fun onClickBackButton() {
        triggerUiEffect(DetailsUiEffect.NavigateBack)
    }
}