package com.akih.moviedb.ui.detail.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.data.source.remote.response.TVShowResponse
import com.akih.moviedb.utils.DummyData
import com.akih.moviedb.vo.Resource
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
    private lateinit var tvObserver: Observer<Resource<TVShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailTVShowViewModel(movieRepository)
        viewModel.setSelectedTVShow(tvShowId)
    }
    
    @Test
    fun getTVShow(){
        val mShow = Resource.success(DummyData.fetchAllShowDetailFavorite(dummyTVShow, true))
        val show = MutableLiveData<Resource<TVShowEntity>>()
        show.value = mShow
        Mockito.`when`(movieRepository.getTVShowDetail(tvShowId)).thenReturn(show)
        viewModel.getTVShow.observeForever(tvObserver)
        verify(tvObserver).onChanged(mShow)
    }

    @Test
    fun setFavMovies(){
        val mShow = MutableLiveData<Resource<TVShowEntity>>()
        mShow.value = Resource.success(DummyData.fetchAllShowDetailFavorite(dummyTVShow, true))
        Mockito.`when`(movieRepository.getTVShowDetail(tvShowId)).thenReturn(mShow)
        viewModel.setFavoritShow()
        viewModel.getTVShow.observeForever(tvObserver)
        verify(tvObserver).onChanged(mShow.value)
        val valueExp = mShow.value
        val valueAct = viewModel.getTVShow.value
        TestCase.assertEquals(valueExp, valueAct)
    }
}