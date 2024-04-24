package com.ahmadshubita.moviesapp.ui.people.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import androidx.paging.filter
import com.ahmadshubita.moviesapp.base.BaseViewModel
import com.ahmadshubita.moviesapp.data.models.People
import com.ahmadshubita.moviesapp.data.remote.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val mainRepository: MainRepository) :
    BaseViewModel<PeopleScreenState, BaseViewModel.BaseUiEffect>(
        PeopleScreenState()
    ) {

    init {
        onRefreshData()
    }

     private fun getPeople() {
        executePaging(
            call = {mainRepository.getPeople(LANGUAGE_TYPE, 1)},
            onSuccess = { response ->
                onGetPeople(response)
            },
            onError = { onError() }
        )
    }

    private fun onGetPeople(peopleList: PagingData<People>) {
        val filteredList = peopleList.filter { !it.profilePath.isNullOrBlank() }
        uiMutableState.update {
            it.copy(
                    isLoadingState = mutableStateOf(false),
                    isErrorState = mutableStateOf(false),
                    peopleItems = flowOf(filteredList),
            )
        }
    }

    private fun onError() {
        uiMutableState.update { it.copy(isLoadingState = mutableStateOf(false), isErrorState = mutableStateOf(true)) }
    }

    fun onRefreshData() {
        getPeople()
    }

    companion object{
        const val LANGUAGE_TYPE = "en-US"
    }
}