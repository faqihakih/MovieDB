package com.akih.moviedb.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.data.source.local.room.MovieDAO

class LocalMovieDataSource(private val movieDAO: MovieDAO) {
    companion object {
        private var INSTANCE: LocalMovieDataSource? = null

        fun getInstance(movieDAO: MovieDAO): LocalMovieDataSource =
            INSTANCE ?: LocalMovieDataSource(movieDAO).apply { INSTANCE = this }
    }
    fun readAllMovies() : DataSource.Factory<Int , MovieEntity> = movieDAO.getAllMovies()
    fun readAllShows() : DataSource.Factory<Int , TVShowEntity> = movieDAO.getAllShow()
    fun getDetailMovie(id : Int) : LiveData<MovieEntity> = movieDAO.getMovieById(id)
    fun getDetailShow(id : Int) : LiveData<TVShowEntity> = movieDAO.getShowById(id)
    fun insertMovies(movie: List<MovieEntity>) = movieDAO.insertMovie(movie)
    fun insertShows(movie: List<TVShowEntity>) = movieDAO.insertShow(movie)
    fun updateMovies(movie: MovieEntity, id: Int) = movieDAO.updateMovie(movie)
    fun updateShows(movie: TVShowEntity, id: Int) = movieDAO.updateShow(movie)
    fun setFavMovie(movie: MovieEntity, value : Boolean){
        movie.favorite = value
        movieDAO.updateMovie(movie)
    }
    fun setFavShow(movie: TVShowEntity, value : Boolean){
        movie.favorite = value
        movieDAO.updateShow(movie)
    }
    fun getFavMovies():DataSource.Factory<Int, MovieEntity> = movieDAO.getFavMovie()
    fun getFavShows():DataSource.Factory<Int, TVShowEntity> = movieDAO.getFaShow()
}