package com.ahmadshubita.moviesapp.ui.movies.details.viewmodel

import com.ahmadshubita.moviesapp.base.BaseViewModel

interface DetailsUiEffect : BaseViewModel.BaseUiEffect {
    object NavigateBack : DetailsUiEffect
}