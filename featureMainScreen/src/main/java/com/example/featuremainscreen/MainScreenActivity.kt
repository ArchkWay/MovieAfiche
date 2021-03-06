package com.example.featuremainscreen

import ConnectivityHelper
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.dataBase.MovieSaved
import com.example.featuremainscreen.MovieViewModel.Companion.TWENTY_TWENTY
import kotlinx.android.synthetic.main.activity_main_screen.*


class MainScreenActivity : AppCompatActivity() {

    private var adapter: MovieAdapter? = null
    private var spanCount = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 5 else 3
        setContentView(R.layout.activity_main_screen)
        recyclerView.layoutManager = GridLayoutManager(this, spanCount)
        recyclerView.setHasFixedSize(true)
        adapter = MovieAdapter()
        recyclerView.adapter = adapter

        val movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.allMovies?.observe(this,
            { movies ->
                setMovies(movies)
                movieViewModel.list = movies
            })

        if(ConnectivityHelper.isConnectedToNetwork(this)) movieViewModel.getMoviesFromNet()
        else Toast.makeText(this, getString(R.string.connection_lost), Toast.LENGTH_SHORT).show()

        yearSwitcher.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                setMovies(movieViewModel.list?.filter { it.year == TWENTY_TWENTY })
                buttonView?.text = resources.getString(R.string.all_movies)
            } else setMovies(movieViewModel.list).also {
                buttonView?.text = resources.getString(R.string.only_2020)
            }
        }
    }

    private fun setMovies(movies: List<MovieSaved?>?) {
        adapter?.setMovies(movies)
    }
}
