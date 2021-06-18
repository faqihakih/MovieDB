package com.akih.moviedb.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.data.source.remote.response.MovieResponse
import com.akih.moviedb.data.source.remote.response.TVShowResponse
import com.akih.moviedb.data.source.remote.response.ApiResponse
import com.akih.moviedb.utils.JSONHelper

class RemoteDataSource private constructor(private val jsonHelper: JSONHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JSONHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(helper).apply { instance = this }
            }
    }
    fun getMovie(): LiveData<ApiResponse<List<MovieEntity>>> {
        val resultMovie = MutableLiveData<ApiResponse<List<MovieEntity>>>()
        resultMovie.value = ApiResponse.success(jsonHelper.loadMovie())
        return resultMovie
    }

    interface LoadMovie{
        fun onAllMovieReceived(myMovieResponse: List<MovieResponse>)
    }

    fun getShow(): LiveData<ApiResponse<List<TVShowEntity>>> {
        val resultTv = MutableLiveData<ApiResponse<List<TVShowEntity>>>()
        resultTv.value = ApiResponse.success(jsonHelper.loadTVShow())
        return resultTv
    }

    interface LoadShow{
        fun onAllMTvReceived(myTvResponse: List<TVShowResponse>)
    }


    fun getMovieDetail(id:Int): LiveData<ApiResponse<MovieEntity>> {
        val myMovieDetail = MutableLiveData<ApiResponse<MovieEntity>>()
        myMovieDetail.value = ApiResponse.success(jsonHelper.loadMovieDetail(id))
        return myMovieDetail
    }

    interface loadDetailMovie{
        fun onDetailMyMovie(myDetailMovieResponse : MovieResponse)
    }


    fun getShowDetail(id:Int): LiveData<ApiResponse<TVShowEntity>> {
        val myTvDetail = MutableLiveData<ApiResponse<TVShowEntity>>()
        myTvDetail.value = ApiResponse.success(jsonHelper.loadTVShowDetail(id))
        return myTvDetail
    }

    interface loadDetailShow{
        fun onDetailMyTv(myDetailTvesponse : TVShowResponse)
    }

}