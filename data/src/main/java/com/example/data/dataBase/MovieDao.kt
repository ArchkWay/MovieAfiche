package com.example.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Insert
    fun insert(movieSaved: MovieSaved?)

    @Update
    fun update(movieSaved: MovieSaved?)

    @Delete
    fun delete(movieSaved: MovieSaved?)

    @Query("DELETE FROM movie_table")
    fun deleteAllMovies()

    @Query("SELECT * FROM movie_table ORDER by year DESC")
    fun getMoviesAll(): LiveData<List<MovieSaved>>

}