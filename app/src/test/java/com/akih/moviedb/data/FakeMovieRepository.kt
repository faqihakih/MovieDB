package com.akih.moviedb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akih.moviedb.data.source.remote.RemoteDataSource
import com.akih.moviedb.data.source.remote.response.MovieResponse
import com.akih.moviedb.data.source.remote.response.TVShowResponse

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    override fun getAllMovie(): LiveData<List<MovieResponse>> {
        val movieResponse = MutableLiveData<List<MovieResponse>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponses: List<MovieResponse>) {
                val listMovie = ArrayList<MovieResponse>()
                for (response in moviesResponses) {
                    val movie = MovieResponse(
                            response.id,
                            response.title,
                            response.year,
                            response.duration,
                            response.rating,
                            response.genre,
                            response.synopsis,
                            response.banner,
                            response.trailer
                    )
                    listMovie.add(movie)
                }
                movieResponse.postValue(listMovie)
            }
        })

        return movieResponse
    }

    override fun getAllTVShow(): LiveData<List<TVShowResponse>> {
        val tvShowResponse = MutableLiveData<List<TVShowResponse>>()
        remoteDataSource.getAllTVShow(object : RemoteDataSource.LoadTVShowCallback {
            override fun onAllTVShowReceived(tvShowResponses: List<TVShowResponse>){
                val listTVShow = ArrayList<TVShowResponse>()
                for (response in tvShowResponses) {
                    val tvShow = TVShowResponse(
                            response.id,
                            response.title,
                            response.year,
                            response.duration,
                            response.rating,
                            response.genre,
                            response.synopsis,
                            response.banner,
                            response.trailer
                    )
                    listTVShow.add(tvShow)
                }
                tvShowResponse.postValue(listTVShow)
            }
        })

        return tvShowResponse
    }

    override fun getMovieDetail(MovieId: Int): LiveData<MovieResponse> {
        val movieDetailResponse = MutableLiveData<MovieResponse>()

        remoteDataSource.getMoviesDetail(MovieId, object : RemoteDataSource.LoadMoviesDetailCallback{
            override fun onAllMoviesDetailReceived(movieDetailResponses: MovieResponse) {
                val movieDetail = MovieResponse(
                        movieDetailResponses.id,
                        movieDetailResponses.title,
                        movieDetailResponses.year,
                        movieDetailResponses.duration,
                        movieDetailResponses.rating,
                        movieDetailResponses.genre,
                        movieDetailResponses.synopsis,
                        movieDetailResponses.banner,
                        movieDetailResponses.trailer
                )
                movieDetailResponse.postValue(movieDetail)
            }
        })
        return movieDetailResponse
    }

    override fun getTVShowDetail(TVShowId: Int): LiveData<TVShowResponse> {
        val tvShowDetailResponse = MutableLiveData<TVShowResponse>()

        remoteDataSource.getTVShowDetail(TVShowId, object : RemoteDataSource.LoadTVShowDetailCallback{
            override fun onAllTVShowDetailReceived(tvShowDetailResponses: TVShowResponse) {
                val tvShowDetail = TVShowResponse(
                        tvShowDetailResponses.id,
                        tvShowDetailResponses.title,
                        tvShowDetailResponses.year,
                        tvShowDetailResponses.duration,
                        tvShowDetailResponses.rating,
                        tvShowDetailResponses.genre,
                        tvShowDetailResponses.synopsis,
                        tvShowDetailResponses.banner,
                        tvShowDetailResponses.trailer
                )
                tvShowDetailResponse.postValue(tvShowDetail)
            }
        })
        return tvShowDetailResponse
    }

}