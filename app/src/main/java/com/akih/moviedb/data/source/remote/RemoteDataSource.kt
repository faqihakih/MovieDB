package com.akih.moviedb.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.data.source.local.room.Movie
import com.akih.moviedb.data.source.local.room.TVShow
import com.akih.moviedb.utils.ApiResponse
import com.akih.moviedb.utils.JSONHelper
import com.akih.moviedb.utils.Resource

class RemoteDataSource private constructor(private val jsonHelper: JSONHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JSONHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(helper).apply { instance = this }
            }
    }
    fun getMovie(): LiveData<ApiResponse<List<Movie>>> {
        val resultMovie = MutableLiveData<ApiResponse<List<Movie>>>()
        resultMovie.value = ApiResponse.success(jsonHelper.loadMovie())
        return resultMovie
    }

    interface LoadMovie{
        fun onAllMovieReceived(myMovieResponse: List<Movie>)
    }

    fun getShow(): LiveData<ApiResponse<List<TVShow>>> {
        val resultTv = MutableLiveData<ApiResponse<List<TVShow>>>()
        resultTv.value = ApiResponse.success(jsonHelper.loadTVShow())
        return resultTv
    }

    interface LoadShow{
        fun onAllMTvReceived(myTvResponse: List<TVShow>)
    }


    fun getMovieDetail(id:Int): LiveData<ApiResponse<Movie>> {
        val myMovieDetail = MutableLiveData<ApiResponse<Movie>>()
        myMovieDetail.value = ApiResponse.success(jsonHelper.loadMovieDetail(id))
        return myMovieDetail
    }

    interface loadDetailMovie{
        fun onDetailMyMovie(myDetailMovieResponse : Movie)
    }


    fun getShowDetail(id:Int): LiveData<ApiResponse<TVShow>> {
        val myTvDetail = MutableLiveData<ApiResponse<TVShow>>()
        myTvDetail.value = ApiResponse.success(jsonHelper.loadTVShowDetail(id))
        return myTvDetail
    }

    interface loadDetailShow{
        fun onDetailMyTv(myDetailTvesponse : TVShow)
    }

}