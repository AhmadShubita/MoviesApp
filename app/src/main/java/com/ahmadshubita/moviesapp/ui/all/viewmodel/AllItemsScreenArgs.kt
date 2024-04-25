package com.ahmadshubita.moviesapp.ui.all.viewmodel

import androidx.lifecycle.SavedStateHandle

class AllItemsScreenArgs(savedStateHandle: SavedStateHandle) {
    val isPopularItemsScreen: Boolean = checkNotNull(savedStateHandle[IS_POPULAR])

    companion object {
        const val IS_POPULAR = "isPopular"
    }
}