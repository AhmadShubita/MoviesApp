package com.ahmadshubita.moviesapp.ui.all.viewmodel

import com.ahmadshubita.moviesapp.base.BaseViewModel

interface AllItemsUiEffect : BaseViewModel.BaseUiEffect {
    object NavigateBack : AllItemsUiEffect
    object NavigateToDetails: AllItemsUiEffect
}