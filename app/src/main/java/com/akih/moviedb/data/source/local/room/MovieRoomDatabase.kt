package com.akih.moviedb.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity

@Database(entities = [Movie::class, TVShow::class], version = 1)
abstract class MovieRoomDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDAO
    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): MovieRoomDatabase {
            if (INSTANCE == null) {
                synchronized(MovieRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MovieRoomDatabase::class.java, "movie_database")
                        .build()
                }
            }
            return INSTANCE as MovieRoomDatabase
        }
    }
}