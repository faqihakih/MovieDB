package com.akih.moviedb.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.akih.moviedb.data.source.local.room.Movie
import com.akih.moviedb.data.source.local.room.TVShow
import com.akih.moviedb.utils.Resource

interface MovieDataSource {
    fun getAllMovie() : LiveData<Resource<PagedList<Movie>>>
    fun getAllTVShow() : LiveData<Resource<PagedList<TVShow>>>
    fun getMovieDetail(MovieId : Int) : LiveData<Resource<Movie>>
    fun getTVShowDetail(TVShowId : Int) : LiveData<Resource<TVShow>>
    fun setFavMovie(movieId : Movie, value : Boolean)
    fun setFavTVShow(TVShowId : TVShow, value : Boolean)
    fun getFavMovie(): LiveData<PagedList<Movie>>
    fun getFavTVShow(): LiveData<PagedList<TVShow>>
}