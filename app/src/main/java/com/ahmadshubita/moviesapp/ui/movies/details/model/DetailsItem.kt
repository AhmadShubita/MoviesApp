package com.ahmadshubita.moviesapp.ui.movies.details.model

import com.ahmadshubita.moviesapp.BuildConfig
import com.ahmadshubita.moviesapp.data.models.MovieDetails
import com.ahmadshubita.moviesapp.data.models.ProductionCompany
import com.ahmadshubita.moviesapp.data.models.Series
import com.ahmadshubita.moviesapp.ui.util.DatesUtil
import java.text.DecimalFormat

data class DetailsItem(
    val id: Int? = -1,
    val overview: String? = "",
    val popularity: Double? = -0.0,
    val posterPath: String? = "",
    val productionCompanies: List<ProductionCompany>? = emptyList(),
    val releaseDate: String? = "",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val voteAverage: Double? = -0.0,
    val voteCount: Int ? = -1
) {

    val posterImageUrl get() = BuildConfig.BASE_IMAGES_URL + posterPath

    val rating get() = DecimalFormat("#.#").format(voteAverage).toString()

    val releaseYear get() = DatesUtil.getYear(releaseDate)


    companion object {
        fun from(
            movieDetails: MovieDetails
        ): DetailsItem {
            return with(movieDetails) {
                DetailsItem(
                    id,
                    overview,
                    popularity,
                    posterPath,
                    productionCompanies ?: emptyList(),
                    releaseDate,
                    tagline,
                    title,
                    video,
                    voteAverage,
                    voteCount
                )
            }
        }

        fun from(
            series: Series
        ): DetailsItem {
            return with(series) {
                DetailsItem(
                    id,
                    overview,
                    popularity,
                    posterPath,
                    productionCompanies ?: emptyList(),
                    firstAirDate,
                    tagline,
                    originalName,
                    false,
                    voteAverage,
                    voteCount
                )
            }
        }
    }
}





