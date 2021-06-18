package com.akih.moviedb.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.data.source.local.room.Movie
import com.akih.moviedb.data.source.local.room.MovieDAO
import com.akih.moviedb.data.source.local.room.TVShow

class LocalMovieDataSource(private val movieDAO: MovieDAO) {
    companion object {
        private var INSTANCE: LocalMovieDataSource? = null

        fun getInstance(movieDAO: MovieDAO): LocalMovieDataSource =
            INSTANCE ?: LocalMovieDataSource(movieDAO).apply { INSTANCE = this }
    }
    fun readAllMovies() : DataSource.Factory<Int , Movie> = movieDAO.getAllMovies()
    fun readAllShows() : DataSource.Factory<Int , TVShow> = movieDAO.getAllShow()
    fun getDetailMovie(id : Int) : LiveData<Movie> = movieDAO.getMovieById(id)
    fun getDetailShow(id : Int) : LiveData<TVShow> = movieDAO.getShowById(id)
    fun insertMovies(movie: List<Movie>) = movieDAO.insertMovie(movie)
    fun insertShows(movie: List<TVShow>) = movieDAO.insertShow(movie)
    fun updateMovies(movie: Movie, id: Int) = movieDAO.updateMovie(movie)
    fun updateShows(movie: TVShow, id: Int) = movieDAO.updateShow(movie)
    fun setFavMovie(movie: Movie, value : Boolean){
        movie.favorite = value
        movieDAO.updateMovie(movie)
    }
    fun setFavShow(movie: TVShow, value : Boolean){
        movie.favorite = value
        movieDAO.updateShow(movie)
    }
    fun getFavMovies():DataSource.Factory<Int, Movie> = movieDAO.getFavMovie()
    fun getFavShows():DataSource.Factory<Int, TVShow> = movieDAO.getFaShow()
}