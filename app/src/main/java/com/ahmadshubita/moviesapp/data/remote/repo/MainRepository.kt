package com.ahmadshubita.moviesapp.data.remote.repo

import androidx.paging.PagingData
import com.ahmadshubita.moviesapp.data.models.Movie
import com.ahmadshubita.moviesapp.data.models.MoviesResponse
import com.ahmadshubita.moviesapp.data.models.People
import com.ahmadshubita.moviesapp.data.models.Tv
import com.ahmadshubita.moviesapp.ui.movies.details.model.DetailsItem
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

    suspend fun getNowPlayingMoviesPaging(
        language: String,
        page: Int
    ): Flow<PagingData<Movie>>

    suspend fun getPopularMoviesPaging(
        language: String,
        page: Int
    ): Flow<PagingData<Movie>>

    suspend fun getTvTopRated(
        language: String,
        page: Int
    ): Flow<PagingData<Tv>>

    suspend fun getPeople(
        language: String,
        page: Int
    ): Flow<PagingData<People>>

    suspend fun getMovieById(
        movieId: Int
    ): DetailsItem

    suspend fun getTvSeriesById(
        tvId: Int
    ): DetailsItem
}