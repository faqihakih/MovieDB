package com.akih.moviedb.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.remote.response.MovieResponse
import com.akih.moviedb.utils.DummyData
import com.akih.moviedb.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutonRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp(){
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun testGetAllMovie() {
        val dummyData = Resource.success(pagedList)
        Mockito.`when`(dummyData.data?.size).thenReturn(10)
        val mMovie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        mMovie.value = dummyData

        Mockito.`when`(movieRepository.getAllMovie()).thenReturn(mMovie)
        val movies = viewModel.getAllMovie().value?.data
        Mockito.verify(movieRepository).getAllMovie()

        viewModel.getAllMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyData)

        assertNotNull(movies)
        assertEquals(10, movies?.size)
    }
}