package com.akih.moviedb.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.akih.moviedb.data.source.local.LocalMovieDataSource
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.data.source.remote.RemoteDataSource
import com.akih.moviedb.data.source.remote.response.ApiResponse
import com.akih.moviedb.utils.AppExecutor
import com.akih.moviedb.vo.Resource

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
                MovieRepository(remoteData, localData, appExecutor).apply { instance = this }
            }
    }

    override fun getAllMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieEntity>>(appExecutor) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.readAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>> =
                    remoteDataSource.getMovie()

            public override fun saveCallResult(data: List<MovieEntity>) {
                val courseList = ArrayList<MovieEntity>()
                for (response in data) {
                    val course = MovieEntity(
                            response.id,
                            response.title,
                            response.year,
                            response.duration,
                            response.rating,
                            response.genre,
                            response.synopsis,
                            response.banner,
                            response.trailer)
                    courseList.add(course)
                }

                localDataSource.insertMovies(courseList)
            }
        }.asLiveData()
    }

    override fun getAllTVShow(): LiveData<Resource<PagedList<TVShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TVShowEntity>, List<TVShowEntity>>(appExecutor) {
            public override fun loadFromDB(): LiveData<PagedList<TVShowEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.readAllShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TVShowEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TVShowEntity>>> =
                    remoteDataSource.getShow()

            public override fun saveCallResult(data: List<TVShowEntity>) {
                val courseList = ArrayList<TVShowEntity>()
                for (response in data) {
                    val course = TVShowEntity(
                            response.id,
                            response.title,
                            response.year,
                            response.duration,
                            response.rating,
                            response.genre,
                            response.synopsis,
                            response.banner,
                            response.trailer)
                    courseList.add(course)
                }

                localDataSource.insertShows(courseList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(MovieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieEntity>(appExecutor){
            override fun saveCallResult(body: MovieEntity) {
                localDataSource.updateMovies(body, MovieId)
            }

            override fun createCall(): LiveData<ApiResponse<MovieEntity>> {
                return remoteDataSource.getMovieDetail(MovieId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean{
                return data == null
            }

            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getDetailMovie(MovieId)
            }

        }.asLiveData()
    }

    override fun getTVShowDetail(TVShowId: Int): LiveData<Resource<TVShowEntity>> {
        return object : NetworkBoundResource<TVShowEntity, TVShowEntity>(appExecutor){

            override fun saveCallResult(data: TVShowEntity) {
                localDataSource.updateShows(data, TVShowId)
            }

            override fun createCall(): LiveData<ApiResponse<TVShowEntity>> {
                return remoteDataSource.getShowDetail(TVShowId)
            }

            override fun shouldFetch(data: TVShowEntity?): Boolean{
                return data == null
            }

            override fun loadFromDB(): LiveData<TVShowEntity> {
                return localDataSource.getDetailShow(TVShowId)
            }

        }.asLiveData()
    }

    override fun setFavMovie(movieId: MovieEntity, value: Boolean) {
        appExecutor.diskIO().execute{localDataSource.setFavMovie(movieId, value)}
    }

    override fun setFavTVShow(TVShowId: TVShowEntity, value: Boolean) {
        appExecutor.diskIO().execute{localDataSource.setFavShow(TVShowId, value)}
    }

    override fun getFavMovie(): LiveData<PagedList<MovieEntity>> {
        val favorite = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovies(), favorite).build()
    }

    override fun getFavTVShow(): LiveData<PagedList<TVShowEntity>> {
        val favorite = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavShows(), favorite).build()
    }

}