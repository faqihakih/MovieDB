package com.akih.moviedb.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.remote.response.MovieResponse
import com.akih.moviedb.utils.DummyData
import com.akih.moviedb.vo.Resource
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
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp(){
        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie(){
        val mMovie = Resource.success(DummyData.fetchAllMovieDetailFavorite(dummyMovie, true))
        val movies = MutableLiveData<Resource<MovieEntity>>()
        movies.value = mMovie
        Mockito.`when`(movieRepository.getMovieDetail(movieId)).thenReturn(movies)
        viewModel.getMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(mMovie)
    }

    @Test
    fun setFavMovies(){
        val mMovie = MutableLiveData<Resource<MovieEntity>>()
        mMovie.value = Resource.success(DummyData.fetchAllMovieDetailFavorite(dummyMovie, true))
        Mockito.`when`(movieRepository.getMovieDetail(movieId)).thenReturn(mMovie)
        viewModel.setFavoritMovie()
        viewModel.getMovie.observeForever(movieObserver)
        verify(movieObserver).onChanged(mMovie.value)
        val valueExp = mMovie.value
        val valueAct = viewModel.getMovie.value
        assertEquals(valueExp, valueAct)
    }
}