package com.ahmadshubita.moviesapp.data.models

import com.ahmadshubita.moviesapp.BuildConfig
import com.ahmadshubita.moviesapp.ui.util.DatesUtil
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat

data class Movie(
    @SerializedName("adult") val adult: Boolean? = false,
    @SerializedName("backdrop_path") val backdropPath: String? = "",
    @SerializedName("genre_ids") val genreIds: List<Int>? = emptyList(),
    @SerializedName("id") val id: Int,
    @SerializedName("original_language") val originalLanguage: String? = "en-US",
    @SerializedName("original_title") val originalTitle: String? = "",
    @SerializedName("overview") val overview: String? = "",
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("poster_path") val posterPath: String? = "",
    @SerializedName("release_date") val releaseDate: String? = "",
    @SerializedName("title") val title: String? = "",
    @SerializedName("video") val video: Boolean? = false,
    @SerializedName("vote_average") val voteAverage: Double? = 0.0,
    @SerializedName("vote_count") val voteCount: Int?
) {
    val posterImageUrl get() = BuildConfig.BASE_IMAGES_URL + posterPath

    val rating get() = DecimalFormat("#.#").format(voteAverage).toString()

    val releaseYear get() = DatesUtil.getYear(releaseDate)
}
