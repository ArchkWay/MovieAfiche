package com.example.featuremainscreen


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.dataBase.MovieSaved
import kotlinx.android.synthetic.main.movie_item.view.*
import java.util.*


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    private var movies: List<MovieSaved?>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val currentMovie = movies?.get(position)
        Glide.with(holder.itemView.ivPoster)
            .load(currentMovie?.url)
            .into(holder.itemView.ivPoster)
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    fun setMovies(movies: List<MovieSaved?>?) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}