package com.example.featuremainscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.data.NetDataGetter
import com.example.data.dataBase.MovieRepository
import com.example.data.dataBase.MovieSaved
import com.example.domain.entity.MoviesResponse
import kotlinx.coroutines.*


class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository = MovieRepository(application)
    val allMovies: LiveData<List<MovieSaved>>?
    private val job = SupervisorJob()


    fun getMoviesFromNet() {
        CoroutineScope(Dispatchers.IO + job).launch {
            val netDataGetter = NetDataGetter()
            val movies = netDataGetter.getMoviesCall() as? List<MoviesResponse.MoviesResponseItem>
            val moviesSaved = movies?.map {
                MovieSaved(
                    id = it.id?.toInt() ?: 0,
                    year = it.year ?: 2020,
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

    var list: List<MovieSaved>? = null

    init {
        allMovies = repository.allMovies
    }
}

