package com.ahmadshubita.moviesapp.ui.all.viewmodel

import com.ahmadshubita.moviesapp.base.BaseViewModel
import com.ahmadshubita.moviesapp.ui.bottombar.DetailsType

interface AllItemsUiEffect : BaseViewModel.BaseUiEffect {
    object NavigateBack : AllItemsUiEffect
    data class NavigateToDetails(val detailsType: DetailsType, val id: String) : AllItemsUiEffect
}