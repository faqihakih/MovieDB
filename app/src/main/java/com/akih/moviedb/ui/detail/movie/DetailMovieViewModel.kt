package com.akih.moviedb.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.vo.Resource

class DetailMovieViewModel (private val movieRepository: MovieRepository) : ViewModel() {
    private var movieId = MutableLiveData<Int>()
    fun setSelectedMovie(moviesId: Int) {
        this.movieId.value = moviesId
    }

    var getMovie : LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { id ->
        movieRepository.getMovieDetail(id)
    }

    fun setFavoritMovie(){
        val favorite = getMovie.value
        if(favorite != null){
            val idMovie = favorite.data
            if(idMovie != null){
                val value = idMovie.favorite
                movieRepository.setFavMovie(idMovie, value)
            }
        }
    }
}