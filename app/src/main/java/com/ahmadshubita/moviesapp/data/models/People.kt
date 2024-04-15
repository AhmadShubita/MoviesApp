package com.ahmadshubita.moviesapp.data.models

import com.ahmadshubita.moviesapp.BuildConfig
import com.ahmadshubita.moviesapp.ui.util.DatesUtil
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat

data class People(
    @SerializedName("adult")
    val adult: Boolean ? = false,
    @SerializedName("gender")
    val gender: Int ? = 1,
    @SerializedName("id")
    val id: Int,
    @SerializedName("known_for_department")
    val knownForDepartment: String ? = "",
    @SerializedName("original_name")
    val originalName: String ?= "",
    @SerializedName("popularity")
    val popularity: Double ? = 0.0,
    @SerializedName("profile_path")
    val profilePath: String? = "",
) {
    val profileImageUrl get() = BuildConfig.BASE_IMAGES_URL + profilePath

    val rating get() = DecimalFormat("#.#").format(popularity).toString()
}
