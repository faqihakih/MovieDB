package com.akih.moviedb.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.akih.moviedb.data.source.remote.RemoteDataSource
import com.akih.moviedb.utils.DummyData
import com.akih.moviedb.utils.LiveDataUtils
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

class MovieRepositoryTest {
    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val movieResponses = DummyData.fetchAllMovie()
    private val movieDetail = movieResponses[0]
    private val movieId = movieDetail.id
    private val tvShowResponses = DummyData.fetchAllTVShow()
    private val tvShowDetail = tvShowResponses[0]
    private val tvShowId = tvShowDetail.id

    @Test
    fun getAllMovie() {
        doAnswer { i -> (i.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val myMovieEntity  = LiveDataUtils.getValue(movieRepository.getAllMovie())
        verify(remote).getAllMovies(any())
        assertNotNull(myMovieEntity)
        assertEquals(movieResponses.size.toLong(), myMovieEntity.size.toLong())
    }

    @Test
    fun getAllTVShow() {
        doAnswer { i -> (i.arguments[0] as RemoteDataSource.LoadTVShowCallback)
                .onAllTVShowReceived(tvShowResponses)
            null
        }.`when`(remote).getAllTVShow(any())
        val myTvEntity = LiveDataUtils.getValue(movieRepository.getAllTVShow())
        verify(remote).getAllTVShow(any())
        assertNotNull(myTvEntity)
        assertEquals(movieResponses.size.toLong(), myTvEntity.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        doAnswer { i -> (i.arguments[1] as RemoteDataSource.LoadMoviesDetailCallback)
                .onAllMoviesDetailReceived(movieDetail)
            null
        }.`when`(remote).getMoviesDetail((eq(movieId)), any())
        val movieDetailEntity = LiveDataUtils.getValue(movieRepository.getMovieDetail(movieId))
        verify(remote).getMoviesDetail(eq(movieId), any())
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.id)
        assertEquals(movieDetail.title, movieDetailEntity.title)
        assertEquals(movieDetail.year, movieDetailEntity.year)
        assertEquals(movieDetail.duration, movieDetailEntity.duration)
        assertEquals(movieDetail.rating, movieDetailEntity.rating)
        assertEquals(movieDetail.genre, movieDetailEntity.genre)
        assertEquals(movieDetail.synopsis, movieDetailEntity.synopsis)
        assertEquals(movieDetail.banner, movieDetailEntity.banner)
        assertEquals(movieDetail.trailer, movieDetailEntity.trailer)
    }

    @Test
    fun getTVShowDetail() {
        doAnswer { i -> (i.arguments[1] as RemoteDataSource.LoadTVShowDetailCallback)
                .onAllTVShowDetailReceived(tvShowDetail)
            null
        }.`when`(remote).getTVShowDetail((eq(tvShowId)), any())
        val tvDetailEntity = LiveDataUtils.getValue(movieRepository.getTVShowDetail(tvShowId))
        verify(remote).getTVShowDetail(eq(tvShowId), any())
        assertNotNull(tvShowDetail)
        assertEquals(tvShowDetail.id, tvDetailEntity.id)
        assertEquals(tvShowDetail.title, tvDetailEntity.title)
        assertEquals(tvShowDetail.year, tvDetailEntity.year)
        assertEquals(tvShowDetail.duration, tvDetailEntity.duration)
        assertEquals(tvShowDetail.rating, tvDetailEntity.rating)
        assertEquals(tvShowDetail.genre, tvDetailEntity.genre)
        assertEquals(tvShowDetail.synopsis, tvDetailEntity.synopsis)
        assertEquals(tvShowDetail.banner, tvDetailEntity.banner)
        assertEquals(tvShowDetail.trailer, tvDetailEntity.trailer)
    }
}