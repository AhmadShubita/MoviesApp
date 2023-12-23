package com.ahmadshubita.moviesapp.data.remote.repo

import com.ahmadshubita.moviesapp.data.models.MoviesResponse

interface MainRepository {
    suspend fun getTopRatedMovies(
        language: String,
        page: Int
    ): MoviesResponse
}