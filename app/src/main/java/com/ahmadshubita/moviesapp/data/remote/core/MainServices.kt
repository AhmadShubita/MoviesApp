package com.ahmadshubita.moviesapp.data.remote.core

import com.ahmadshubita.moviesapp.data.models.MoviesResponse
import com.ahmadshubita.moviesapp.data.models.PeopleResponse
import com.ahmadshubita.moviesapp.data.models.TvResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MainServices {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("tv/top_rated")
    suspend fun getTvTopRated(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<TvResponse>

    @GET("person/popular")
    suspend fun getPeople(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<PeopleResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int
    ): Response<PeopleResponse>
}