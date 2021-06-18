package com.akih.moviedb.di

import android.content.Context
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.LocalMovieDataSource
import com.akih.moviedb.data.source.local.room.MovieRoomDatabase
import com.akih.moviedb.data.source.remote.RemoteDataSource
import com.akih.moviedb.utils.AppExecutor
import com.akih.moviedb.utils.JSONHelper

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieRoomDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JSONHelper(context))
        val localDataSource = LocalMovieDataSource.getInstance(database.movieDao())
        val appExecutor = AppExecutor()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutor)
    }
}