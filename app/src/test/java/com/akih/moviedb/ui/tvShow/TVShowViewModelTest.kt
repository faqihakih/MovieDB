package com.akih.moviedb.ui.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.remote.response.TVShowResponse
import com.akih.moviedb.utils.DummyData
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
class TVShowViewModelTest {
    private lateinit var viewModel: TVShowViewModel

    @get:Rule
    var instantTaskExecutonRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository


    @Mock
    private lateinit var observer: Observer<List<TVShowResponse>>


    @Before
    fun setUp() {
        viewModel = TVShowViewModel(movieRepository)
    }

    @Test
    fun testGetAllTVShow() {
        val mDummy = DummyData.fetchAllTVShow()
        val mTv = MutableLiveData<List<TVShowResponse>>()
        mTv.value = mDummy
        Mockito.`when`(movieRepository.getAllTVShow()).thenReturn(mTv)
        val tvShows = viewModel.getAllTVShow().value
        Mockito.verify(movieRepository).getAllTVShow()
        viewModel.getAllTVShow().observeForever(observer)
        Mockito.verify(observer).onChanged(mDummy)
        assertNotNull(tvShows)
        assertEquals(10, tvShows?.size)
    }
}