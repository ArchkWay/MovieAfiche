package com.example.data

import com.example.data.retrofit.RetrofitProvider
import com.example.domain.entity.MoviesResponse


class NetDataGetter {

    suspend fun getMoviesCall(): List<MoviesResponse.MoviesResponseItem>? = RetrofitProvider().api.getMovieListAsync().await()

}
