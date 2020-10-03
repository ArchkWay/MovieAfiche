package com.example.data.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieSaved(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val year: Int,
    val url: String,
)