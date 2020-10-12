package com.example.data

import com.example.data.retrofit.RetrofitProvider
import com.example.domain.entity.MoviesResponse


class NetDataGetter {
    val api: Api? = null

    suspend fun getMoviesCall(): MoviesResponse? {
        val api = RetrofitProvider().api
        return api.getMovieList().await()
    }
}
