package com.ahmadshubita.moviesapp.ui.movies.viewmodel

import com.ahmadshubita.moviesapp.base.BaseViewModel
import com.ahmadshubita.moviesapp.ui.bottombar.DetailsType

interface MoviesUiEffect : BaseViewModel.BaseUiEffect {
    data class NavigateToAllItems(val isPopular: Boolean) : MoviesUiEffect

    data class NavigateToDetails(val detailsType: DetailsType, val id: String) : MoviesUiEffect
}