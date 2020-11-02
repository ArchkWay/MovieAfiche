package com.example.data

import com.example.domain.entity.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {
    @GET("ar2code/apitest/master/movies.json")
    fun getMovieListAsync(): Deferred<List<MoviesResponse.MoviesResponseItem>>

}