package com.ahmadshubita.moviesapp.data.remote.repo

import com.ahmadshubita.moviesapp.data.models.MoviesResponse
import com.ahmadshubita.moviesapp.data.remote.core.MainServices
import com.ahmadshubita.moviesapp.data.remote.utils.wrapApiCall
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainServices: MainServices
) : MainRepository {
    override suspend fun getTopRatedMovies(language: String, page: Int): MoviesResponse {
        return wrapApiCall {
            mainServices.getTopRatedMovies(language, page)
        }
    }
}