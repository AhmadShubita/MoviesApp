package com.ahmadshubita.moviesapp.data.remote.repo

import com.ahmadshubita.moviesapp.data.models.MoviesResponse
import com.ahmadshubita.moviesapp.data.models.PeopleResponse
import com.ahmadshubita.moviesapp.data.models.TvResponse

interface MainRepository {
    suspend fun getTopRatedMovies(
        language: String,
        page: Int
    ): MoviesResponse

    suspend fun getNowPlayingMovies(
        language: String,
        page: Int
    ): MoviesResponse

    suspend fun getPopularMovies(
        language: String,
        page: Int
    ): MoviesResponse

    suspend fun getTvTopRated(
        language: String,
        page: Int
    ): TvResponse

    suspend fun getPeople(
        language: String,
        page: Int
    ): PeopleResponse
}