package com.akih.moviedb.ui.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.data.source.remote.response.TVShowResponse
import com.akih.moviedb.utils.DummyData
import com.akih.moviedb.vo.Resource
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
    private lateinit var observer: Observer<Resource<PagedList<TVShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TVShowEntity>

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(movieRepository)
    }

    @Test
    fun testGetAllTVShow() {
        val mDummy = Resource.success(pagedList)
        Mockito.`when`(mDummy.data?.size).thenReturn(10)
        val mTv = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        mTv.value = mDummy

        Mockito.`when`(movieRepository.getAllTVShow()).thenReturn(mTv)
        val tvShows = viewModel.getAllTVShow().value?.data
        Mockito.verify(movieRepository).getAllTVShow()

        viewModel.getAllTVShow().observeForever(observer)
        Mockito.verify(observer).onChanged(mDummy)

        assertNotNull(tvShows)
        assertEquals(10, tvShows?.size)
    }
}