package com.ahmadshubita.moviesapp.data.models

data class Movie(
    val adult: Boolean ? = false,
    val backdropPath: String ? = "",
    val genreIds: List<Int>? = emptyList(),
    val id: Int,
    val originalLanguage: String? = "en-US",
    val originalTitle: String ?= "",
    val overview: String? = "",
    val popularity: Double ?,
    val posterPath: String? = "",
    val releaseDate: String ? = "",
    val title: String ? = "",
    val video: Boolean ? =false,
    val voteAverage: Double ? = 0.0,
    val voteCount: Int?
)
