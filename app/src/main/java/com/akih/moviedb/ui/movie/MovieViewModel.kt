package com.akih.moviedb.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.room.Movie
import com.akih.moviedb.utils.Resource

class MovieViewModel (private val movieRepository: MovieRepository) : ViewModel() {
    fun getAllMovie(): LiveData<Resource<PagedList<Movie>>> = movieRepository.getAllMovie()
}