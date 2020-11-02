package com.example.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieSaved::class], version = 3)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao?

    companion object {
        private var instance: MovieDataBase? = null

        @Synchronized
        fun getInstance(context: Context): MovieDataBase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDataBase::class.java,
                    "movie_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }

}