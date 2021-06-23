package com.akih.moviedb.ui.favorite.tvShowFavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.entity.TVShowEntity

class FavoriteTVShowViewModel(private val repository: MovieRepository): ViewModel(){
    fun getFavorite(): LiveData<PagedList<TVShowEntity>> {
        return repository.getFavTVShow()
    }
}