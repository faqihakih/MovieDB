package com.akih.moviedb.ui.detail.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.remote.response.TVShowResponse
import com.akih.moviedb.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTVShowViewModelTest {

    private lateinit var viewModel: DetailTVShowViewModel
    private val dummyTVShow = DummyData.fetchAllTVShow()[0]
    private val tvShowId = dummyTVShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var tvObserver: Observer<TVShowResponse>

    @Before
    fun setUp() {
        viewModel = DetailTVShowViewModel(movieRepository)
        viewModel.setSelectedTVShow(tvShowId)
    }
    
    @Test
    fun getTVShow(){
        val mTv = MutableLiveData<TVShowResponse>()
        mTv.value = dummyTVShow

        Mockito.`when`(movieRepository.getTVShowDetail(tvShowId)).thenReturn(mTv)
        val dataTVShow = viewModel.getTVShow().value as TVShowResponse
        verify(movieRepository).getTVShowDetail(tvShowId)
        viewModel.getTVShow().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTVShow)
        TestCase.assertNotNull(dataTVShow)
        TestCase.assertEquals(dummyTVShow.id, dataTVShow.id)
        TestCase.assertEquals(dummyTVShow.title, dataTVShow.title)
        TestCase.assertEquals(dummyTVShow.year, dataTVShow.year)
        TestCase.assertEquals(dummyTVShow.duration, dataTVShow.duration)
        TestCase.assertEquals(dummyTVShow.rating, dataTVShow.rating)
        TestCase.assertEquals(dummyTVShow.genre, dataTVShow.genre)
        TestCase.assertEquals(dummyTVShow.synopsis, dataTVShow.synopsis)
        TestCase.assertEquals(dummyTVShow.banner, dataTVShow.banner)
        TestCase.assertEquals(dummyTVShow.trailer, dataTVShow.trailer)
    }
}