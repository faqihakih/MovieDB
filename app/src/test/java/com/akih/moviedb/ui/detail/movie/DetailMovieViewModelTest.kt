package com.akih.moviedb.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.remote.response.MovieResponse
import com.akih.moviedb.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest{

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DummyData.fetchAllMovie()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieResponse>

    @Before
    fun setUp(){
        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie(){
        val mMovie = MutableLiveData<MovieResponse>()
        mMovie.value = dummyMovie

        Mockito.`when`(movieRepository.getMovieDetail(movieId)).thenReturn(mMovie)
        val dataMovie = viewModel.getMovie().value as MovieResponse
        verify(movieRepository).getMovieDetail(movieId)
        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
        assertNotNull(dataMovie)
        assertEquals(dummyMovie.id, dataMovie.id)
        assertEquals(dummyMovie.title, dataMovie.title)
        assertEquals(dummyMovie.year, dataMovie.year)
        assertEquals(dummyMovie.duration, dataMovie.duration)
        assertEquals(dummyMovie.rating, dataMovie.rating)
        assertEquals(dummyMovie.genre, dataMovie.genre)
        assertEquals(dummyMovie.synopsis, dataMovie.synopsis)
        assertEquals(dummyMovie.banner, dataMovie.banner)
        assertEquals(dummyMovie.trailer, dataMovie.trailer)
    }
}