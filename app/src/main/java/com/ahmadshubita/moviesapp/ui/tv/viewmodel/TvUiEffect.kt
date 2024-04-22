package com.ahmadshubita.moviesapp.ui.tv.viewmodel

import com.ahmadshubita.moviesapp.base.BaseViewModel
import com.ahmadshubita.moviesapp.ui.bottombar.DetailsType

interface TvUiEffect : BaseViewModel.BaseUiEffect {
    data class NavigateToDetails(val detailsType: DetailsType, val id: String) : TvUiEffect
}