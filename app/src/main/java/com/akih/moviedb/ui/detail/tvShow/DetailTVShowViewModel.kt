package com.akih.moviedb.ui.detail.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.room.Movie
import com.akih.moviedb.data.source.local.room.TVShow
import com.akih.moviedb.utils.Resource

class DetailTVShowViewModel (private val movieRepository: MovieRepository) : ViewModel() {
    private var showId = MutableLiveData<Int>()
    fun setSelectedTVShow(moviesId: Int) {
        this.showId.value = moviesId
    }

    var getTVShow : LiveData<Resource<TVShow>> = Transformations.switchMap(showId) { id ->
        movieRepository.getTVShowDetail(id)
    }

    fun setFavoritShow(){
        val favorite = getTVShow.value
        if(favorite != null){
            val idShow = favorite.data
            if(idShow != null){
                val value = idShow.favorite
                movieRepository.setFavTVShow(idShow, value)
            }
        }
    }
}