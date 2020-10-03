package com.example.data.dataBase

import android.app.Application
import androidx.lifecycle.LiveData

class MovieRepository(aplication: Application) {
    var movieDao: MovieDao? = null
    var allMovies: LiveData<List<MovieSaved>>? = null

    init {
        val movieDataBase = MovieDataBase.getInstance(aplication)
        movieDao = movieDataBase?.movieDao()
        allMovies = movieDao?.getMoviesAll()
    }

    fun insert(movieSaved: MovieSaved?) {
        movieDao?.insert(movieSaved)
    }

}