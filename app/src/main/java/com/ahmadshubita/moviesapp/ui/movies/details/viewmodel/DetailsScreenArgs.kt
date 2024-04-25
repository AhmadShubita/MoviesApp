package com.ahmadshubita.moviesapp.ui.movies.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.ahmadshubita.moviesapp.ui.bottombar.DetailsType

class DetailsScreenArgs(savedStateHandle: SavedStateHandle) {
    val detailsType: DetailsType = checkNotNull(savedStateHandle[DETAILS_TYPE])
    val id: String = checkNotNull(savedStateHandle[ID])

    companion object {
        const val DETAILS_TYPE = "DETAILS_TYPE"
        const val ID = "id"

    }
}