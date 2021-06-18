package com.akih.moviedb.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.room.TVShow
import com.akih.moviedb.utils.Resource

class TVShowViewModel (private val movieRepository: MovieRepository) : ViewModel() {
    fun getAllTVShow(): LiveData<Resource<PagedList<TVShow>>> = movieRepository.getAllTVShow()
}