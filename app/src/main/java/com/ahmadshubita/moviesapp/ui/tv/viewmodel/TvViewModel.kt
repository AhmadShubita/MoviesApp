package com.ahmadshubita.moviesapp.ui.tv.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.ahmadshubita.moviesapp.BaseViewModel
import com.ahmadshubita.moviesapp.data.remote.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel<TvScreenState>(
        TvScreenState()
    ) {

    init {
        getTvTopRated()
    }

     private fun getTvTopRated() {
        tryToExecute(
            call = {mainRepository.getTvTopRated(LANGUAGE_TYPE, 1)},
            onSuccess = { response ->
                _state.update {
                    it.copy(
                        isLoadingState = mutableStateOf(false),
                        isErrorState = mutableStateOf(false) ,
                        topRatedTvItems = response.results
                    )
                }
            },
            onError = {  }
        )
    }

    companion object{
        const val LANGUAGE_TYPE = "en-US"
    }
}