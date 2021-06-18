package com.akih.moviedb.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.vo.Resource

interface MovieDataSource {
    fun getAllMovie() : LiveData<Resource<PagedList<MovieEntity>>>
    fun getAllTVShow() : LiveData<Resource<PagedList<TVShowEntity>>>
    fun getMovieDetail(MovieId : Int) : LiveData<Resource<MovieEntity>>
    fun getTVShowDetail(TVShowId : Int) : LiveData<Resource<TVShowEntity>>
    fun setFavMovie(movieId : MovieEntity, value : Boolean)
    fun setFavTVShow(TVShowId : TVShowEntity, value : Boolean)
    fun getFavMovie(): LiveData<PagedList<MovieEntity>>
    fun getFavTVShow(): LiveData<PagedList<TVShowEntity>>
}