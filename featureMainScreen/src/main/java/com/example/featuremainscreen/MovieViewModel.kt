package com.example.featuremainscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.data.NetDataGetter
import com.example.data.dataBase.MovieRepository
import com.example.data.dataBase.MovieSaved
import kotlinx.coroutines.*


class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository = MovieRepository(application)
    val allMovies: LiveData<List<MovieSaved>>?
    private val job = SupervisorJob()
    var list: List<MovieSaved>? = null


    fun getMoviesFromNet() {
        CoroutineScope(Dispatchers.IO + job).launch {
            val netDataGetter = NetDataGetter()
            val movies = netDataGetter.getMoviesCall()
            val moviesSaved = movies?.map {
                MovieSaved(
                    id = it.id?.toInt() ?: 0,
                    year = it.year ?: TWENTY_TWENTY,
                    url = it.poster ?: ""
                )
            }
            moviesSaved?.forEach { movie ->
                if (list?.find { it.id == movie.id } == null) {
                    repository.insert(movie)
                }
            }
        }

    }


    init {
        allMovies = repository.allMovies
    }

    companion object {
        const val TWENTY_TWENTY = 2020
    }
}

