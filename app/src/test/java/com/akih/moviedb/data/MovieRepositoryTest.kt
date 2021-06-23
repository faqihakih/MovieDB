package com.akih.moviedb.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.akih.moviedb.data.source.local.LocalMovieDataSource
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.data.source.remote.RemoteDataSource
import com.akih.moviedb.utils.*
import com.akih.moviedb.vo.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.mock

class MovieRepositoryTest {
    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalMovieDataSource::class.java)
    private val execute = mock(AppExecutor::class.java)
    private val movieRepository = FakeMovieRepository(remote, local, execute)

    private val movieResponses = DummyData.fetchAllMovie()
    private val movieDetail = movieResponses[0]
    private val movieId = movieDetail.id
    private val tvShowResponses = DummyData.fetchAllTVShow()
    private val tvShowDetail = tvShowResponses[0]
    private val tvShowId = tvShowDetail.id

    @Test
    fun getAllMovie() {
        val movie = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.readAllMovies()).thenReturn(movie)
        movieRepository.getAllMovie()

        val MovieEntity  = Resource.success(PagingUtils.pagedList(DummyData.fetchAllMovie()))
        verify(local).readAllMovies()
        assertNotNull(MovieEntity.data)
        assertEquals(movieResponses.size.toLong(), MovieEntity.data?.size?.toLong())
    }

    @Test
    fun getAllTVShow() {
        val movie = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        Mockito.`when`(local.readAllShows()).thenReturn(movie)
        movieRepository.getAllTVShow()

        val showEntity  = Resource.success(PagingUtils.pagedList(DummyData.fetchAllTVShow()))
        verify(local).readAllShows()
        assertNotNull(showEntity.data)
        assertEquals(movieResponses.size.toLong(), showEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val MoviesEntity = MutableLiveData<MovieEntity>()
        MoviesEntity.value = DummyData.fetchAllMovieDetail(movieId)
        Mockito.`when`(local.getDetailMovie(movieId)).thenReturn(MoviesEntity)

        val moviesEntities = LiveDataUtilsTest.getValue(movieRepository.getMovieDetail(movieId))
        verify(local).getDetailMovie(movieId)

        assertNotNull(moviesEntities)
        assertNotNull(moviesEntities.data?.title)
        assertEquals(movieResponses[0].title, moviesEntities.data?.title)
    }

    @Test
    fun getTVShowDetail() {
        val ShowEntity = MutableLiveData<TVShowEntity>()
        ShowEntity.value = DummyData.fetchAllShowDetail(tvShowId)
        Mockito.`when`(local.getDetailShow(tvShowId)).thenReturn(ShowEntity)

        val showEntities = LiveDataUtilsTest.getValue(movieRepository.getTVShowDetail(tvShowId))
        verify(local).getDetailShow(tvShowId)

        assertNotNull(showEntities)
        assertNotNull(showEntities.data?.title)
        assertEquals(tvShowResponses[0].title, showEntities.data?.title)
    }

    @Test
    fun getFavoriteMovie(){
        val dataMovie = mock(DataSource.Factory::class.java)as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getFavMovies()).thenReturn(dataMovie)

        movieRepository.getFavMovie()

        val movieEntity = Resource.success(PagingUtils.pagedList(DummyData.fetchAllMovie()))
        verify(local).getFavMovies()
        assertNotNull(movieEntity)
        assertEquals(movieResponses.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getFavoriteShow(){
        val dataMovie = mock(DataSource.Factory::class.java)as DataSource.Factory<Int, TVShowEntity>
        Mockito.`when`(local.getFavShows()).thenReturn(dataMovie)

        movieRepository.getFavTVShow()

        val movieEntity = Resource.success(PagingUtils.pagedList(DummyData.fetchAllTVShow()))
        verify(local).getFavShows()
        assertNotNull(movieEntity)
        assertEquals(tvShowResponses.size.toLong(), movieEntity.data?.size?.toLong())
    }
}