package com.ahmadshubita.moviesapp.ui.people.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.ahmadshubita.moviesapp.base.BaseUiEffect
import com.ahmadshubita.moviesapp.base.BaseViewModel
import com.ahmadshubita.moviesapp.data.remote.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel<PeopleScreenState, BaseUiEffect>(
        PeopleScreenState()
    ) {

    init {
        getPeople()
    }

     private fun getPeople() {
        tryToExecute(
            call = {mainRepository.getPeople(LANGUAGE_TYPE, 1)},
            onSuccess = { response ->
                _uiState.update {
                    it.copy(
                        isLoadingState = mutableStateOf(false),
                        isErrorState = mutableStateOf(false) ,
                        peopleItems = response.results
                    )
                }
            },
            onError = {
                _uiState.update {
                    it.copy(
                        isLoadingState = mutableStateOf(false),
                        isErrorState = mutableStateOf(true)
                    )
                }
            }
        )
    }

    companion object{
        const val LANGUAGE_TYPE = "en-US"
    }
}