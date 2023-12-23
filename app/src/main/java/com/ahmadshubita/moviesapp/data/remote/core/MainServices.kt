package com.ahmadshubita.moviesapp.data.remote.core

import com.ahmadshubita.moviesapp.data.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MainServices {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>
}