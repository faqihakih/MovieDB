package com.akih.moviedb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akih.moviedb.data.source.remote.RemoteDataSource
import com.akih.moviedb.data.source.remote.response.Movie
import com.akih.moviedb.data.source.remote.response.TVShow

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    override fun getAllMovie(): LiveData<List<Movie>> {
        val movieResponse = MutableLiveData<List<Movie>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponses: List<Movie>) {
                val listMovie = ArrayList<Movie>()
                for (response in moviesResponses) {
                    val movie = Movie(
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

    override fun getAllTVShow(): LiveData<List<TVShow>> {
        val tvShowResponse = MutableLiveData<List<TVShow>>()
        remoteDataSource.getAllTVShow(object : RemoteDataSource.LoadTVShowCallback {
            override fun onAllTVShowReceived(tvShowResponses: List<TVShow>){
                val listTVShow = ArrayList<TVShow>()
                for (response in tvShowResponses) {
                    val tvShow = TVShow(
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

    override fun getMovieDetail(MovieId: Int): LiveData<Movie> {
        val movieDetailResponse = MutableLiveData<Movie>()

        remoteDataSource.getMoviesDetail(MovieId, object : RemoteDataSource.LoadMoviesDetailCallback{
            override fun onAllMoviesDetailReceived(movieDetailResponses: Movie) {
                val movieDetail = Movie(
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

    override fun getTVShowDetail(TVShowId: Int): LiveData<TVShow> {
        val tvShowDetailResponse = MutableLiveData<TVShow>()

        remoteDataSource.getTVShowDetail(TVShowId, object : RemoteDataSource.LoadTVShowDetailCallback{
            override fun onAllTVShowDetailReceived(tvShowDetailResponses: TVShow) {
                val tvShowDetail = TVShow(
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