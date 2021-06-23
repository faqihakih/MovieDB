package com.akih.moviedb.ui.favorite.tvShowFavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.ui.favorite.movieFavorite.FavoriteMovieViewModel
import com.akih.moviedb.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteTVShowViewModelTest {

    private lateinit var viewModel: FavoriteTVShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<TVShowEntity>>

    @Before
    fun setUp(){
        viewModel = FavoriteTVShowViewModel(movieRepository)
    }

    @Test
    fun getFavotiteSuccess() {
        val mShow = MutableLiveData<PagedList<TVShowEntity>>()
        mShow.value = PagedTestDataSources.snapshot(DummyData.fetchAllTVShow())

        Mockito.`when`(movieRepository.getFavTVShow()).thenReturn(mShow)

        viewModel.getFavorite().observeForever(movieObserver)
        verify(movieObserver).onChanged(mShow.value)

        val valueExp = mShow.value
        val valueAct = viewModel.getFavorite().value
        assertEquals(valueExp, valueAct)
        assertEquals(valueAct?.snapshot(), valueAct?.snapshot())
        assertEquals(valueExp?.size, valueAct?.size)
    }

    @Test
    fun getFavoriteEmpty(){
        val mShow = MutableLiveData<PagedList<TVShowEntity>>()
        mShow.value = PagedTestDataSources.snapshot()

        Mockito.`when`(movieRepository.getFavTVShow()).thenReturn(mShow)

        viewModel.getFavorite().observeForever(movieObserver)
        verify(movieObserver).onChanged(mShow.value)

        val valueActDataSize = viewModel.getFavorite().value?.size
        Assert.assertTrue("size of data should be 0, actual is $valueActDataSize", valueActDataSize == 0)
    }

    class PagedTestDataSources private constructor(private val items: List<TVShowEntity>): PositionalDataSource<TVShowEntity>() {
        companion object {
            fun snapshot(items: List<TVShowEntity> = listOf()): PagedList<TVShowEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(
                params: LoadInitialParams,
                callback: LoadInitialCallback<TVShowEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TVShowEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}