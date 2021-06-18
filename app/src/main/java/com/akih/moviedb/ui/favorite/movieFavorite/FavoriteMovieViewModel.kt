package com.akih.moviedb.ui.favorite.movieFavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel (private val repository: MovieRepository): ViewModel(){
    fun getFavorite():LiveData<PagedList<MovieEntity>>{
        return repository.getFavMovie()
    }
}