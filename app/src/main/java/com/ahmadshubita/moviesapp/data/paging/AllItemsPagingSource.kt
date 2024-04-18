package com.ahmadshubita.moviesapp.data.paging

import com.ahmadshubita.moviesapp.data.models.Movie
import com.ahmadshubita.moviesapp.data.models.People
import com.ahmadshubita.moviesapp.data.remote.core.MainServices
import com.ahmadshubita.moviesapp.data.remote.utils.wrapApiCall
import javax.inject.Inject

class AllItemsPagingSource @Inject constructor(
        mainServices: MainServices,
        private val language: String,
        private val isPopularItemScreen: Boolean
) : BasePagingSource<Movie>(mainServices) {
    override suspend fun loadData(page: Int): List<Movie> {
        return wrapApiCall {
            if(isPopularItemScreen) {
                mainServices.getPopularMovies(
                        language = language,
                        page = page
                )
            }else {
                mainServices.getNowPlayingMovies(
                        language = language,
                        page = page
                )
            }
        }.results
    }

    override suspend fun getTotalPages(page: Int): Int {
        return wrapApiCall {
            if(isPopularItemScreen) {
                mainServices.getPopularMovies(
                        language = language,
                        page = page
                )
            }else {
                mainServices.getNowPlayingMovies(
                        language = language,
                        page = page
                )
            }
        }.totalPages
    }
}
