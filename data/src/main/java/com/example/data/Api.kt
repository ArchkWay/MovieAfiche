package com.example.data

import com.example.domain.entity.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("ar2code/apitest/master/movies.json")
    fun getMovieList(): Call<MoviesResponse>

}