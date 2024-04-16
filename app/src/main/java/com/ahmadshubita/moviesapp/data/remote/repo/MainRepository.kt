package com.ahmadshubita.moviesapp.data.remote.repo

import androidx.paging.PagingData
import com.ahmadshubita.moviesapp.data.models.MoviesResponse
import com.ahmadshubita.moviesapp.data.models.PeopleResponse
import com.ahmadshubita.moviesapp.data.models.Tv
import com.ahmadshubita.moviesapp.data.models.TvResponse
import kotlinx.coroutines.flow.Flow

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
    ): Flow<PagingData<Tv>>

    suspend fun getPeople(
        language: String,
        page: Int
    ): PeopleResponse
}