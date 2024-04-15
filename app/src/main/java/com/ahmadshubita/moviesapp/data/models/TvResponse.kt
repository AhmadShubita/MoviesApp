package com.ahmadshubita.moviesapp.data.models

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Tv>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
