package com.example.domain.entity


import com.google.gson.annotations.SerializedName
import java.util.*

class MoviesResponse {
    data class MoviesResponseItem(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("poster")
        val poster: String? = null,
        @SerializedName("year")
        val year: Int? = null
    )
}