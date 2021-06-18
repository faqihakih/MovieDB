package com.akih.moviedb.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.akih.moviedb.data.source.local.LocalMovieDataSource
import com.akih.moviedb.data.source.local.room.Movie
import com.akih.moviedb.data.source.local.room.TVShow
import com.akih.moviedb.data.source.remote.RemoteDataSource
import com.akih.moviedb.utils.ApiResponse
import com.akih.moviedb.utils.AppExecutor
import com.akih.moviedb.utils.NetworkBoundResource
import com.akih.moviedb.utils.Resource

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalMovieDataSource,
    private val appExecutor: AppExecutor,
    ) : MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalMovieDataSource, appExecutor: AppExecutor): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutor).apply { instance = this }
            }
    }

    override fun getAllMovie(): LiveData<Resource<PagedList<Movie>>> {
        return object : NetworkBoundResource<PagedList<Movie>, List<Movie>>(appExecutor){
            override fun saveCallResult(body: List<Movie>) {
                val movieList = ArrayList<Movie>()
                for(response in body){
                    val movieData = Movie(
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
                    movieList.add(movieData)
                    Log.d("movie", movieList.toString())
                }
                localDataSource.insertMovies(movieList)
            }
            override fun createCall(): LiveData<ApiResponse<List<Movie>>> = remoteDataSource.getMovie()
            override fun loadFromDB(): LiveData<PagedList<Movie>> {
                val configData = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.readAllMovies(), configData).build()
            }

            override fun shouldFetch(data: PagedList<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

        }.asLiveData()
    }

    override fun getAllTVShow(): LiveData<Resource<PagedList<TVShow>>> {
        return object : NetworkBoundResource<PagedList<TVShow>, List<TVShow>>(appExecutor){
            override fun saveCallResult(body: List<TVShow>) {
                val movieList = ArrayList<TVShow>()
                for(response in body){
                    val movieData = TVShow(
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
                    movieList.add(movieData)
                    Log.d("movie", movieList.toString())
                }
                localDataSource.insertShows(movieList)
            }
            override fun createCall(): LiveData<ApiResponse<List<TVShow>>> = remoteDataSource.getShow()
            override fun loadFromDB(): LiveData<PagedList<TVShow>> {
                val configData = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.readAllShows(), configData).build()
            }

            override fun shouldFetch(data: PagedList<TVShow>?): Boolean {
                return data == null || data.isEmpty()
            }

        }.asLiveData()
    }

    override fun getMovieDetail(MovieId: Int): LiveData<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, Movie>(appExecutor){
            override fun saveCallResult(body: Movie) {
                localDataSource.updateMovies(body, MovieId)
            }

            override fun createCall(): LiveData<ApiResponse<Movie>> {
                return remoteDataSource.getMovieDetail(MovieId)
            }

            override fun shouldFetch(data: Movie?): Boolean{
                return data == null
            }

            override fun loadFromDB(): LiveData<Movie> {
                return localDataSource.getDetailMovie(MovieId)
            }

        }.asLiveData()
    }

    override fun getTVShowDetail(TVShowId: Int): LiveData<Resource<TVShow>> {
        return object : NetworkBoundResource<TVShow, TVShow>(appExecutor){

            override fun saveCallResult(data: TVShow) {
                localDataSource.updateShows(data, TVShowId)
            }

            override fun createCall(): LiveData<ApiResponse<TVShow>> {
                return remoteDataSource.getShowDetail(TVShowId)
            }

            override fun shouldFetch(data: TVShow?): Boolean{
                return data == null
            }

            override fun loadFromDB(): LiveData<TVShow> {
                return localDataSource.getDetailShow(TVShowId)
            }

        }.asLiveData()
    }

    override fun setFavMovie(movieId: Movie, value: Boolean) {
        appExecutor.diskIO().execute{localDataSource.setFavMovie(movieId, value)}
    }

    override fun setFavTVShow(TVShowId: TVShow, value: Boolean) {
        appExecutor.diskIO().execute{localDataSource.setFavShow(TVShowId, value)}
    }

    override fun getFavMovie(): LiveData<PagedList<Movie>> {
        val favorite = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovies(), favorite).build()
    }

    override fun getFavTVShow(): LiveData<PagedList<TVShow>> {
        val favorite = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavShows(), favorite).build()
    }

}