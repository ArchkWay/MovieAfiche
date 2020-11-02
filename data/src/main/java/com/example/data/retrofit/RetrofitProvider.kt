package com.example.data.retrofit

import com.example.data.Api
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    private val retrofit: Retrofit
    val api: Api

    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/"
    }


    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(OkHttpClient())
            .build()
        api = retrofit.create(Api::class.java)
    }
}