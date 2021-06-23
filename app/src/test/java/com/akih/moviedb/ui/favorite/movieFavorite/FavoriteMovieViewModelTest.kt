package com.akih.moviedb.ui.favorite.movieFavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.akih.moviedb.data.MovieRepository
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.ui.detail.movie.DetailMovieViewModel
import com.akih.moviedb.utils.DummyData
import com.akih.moviedb.vo.Resource
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
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Before
    fun setUp(){
        viewModel = FavoriteMovieViewModel(movieRepository)
    }

    @Test
    fun getFavotiteSuccess() {
        val mMovie = MutableLiveData<PagedList<MovieEntity>>()
        mMovie.value = PagedTestDataSources.snapshot(DummyData.fetchAllMovie())

        Mockito.`when`(movieRepository.getFavMovie()).thenReturn(mMovie)

        viewModel.getFavorite().observeForever(movieObserver)
        verify(movieObserver).onChanged(mMovie.value)

        val valueExp = mMovie.value
        val valueAct = viewModel.getFavorite().value
        assertEquals(valueExp, valueAct)
        assertEquals(valueAct?.snapshot(), valueAct?.snapshot())
        assertEquals(valueExp?.size, valueAct?.size)
    }

    @Test
    fun getFavotiteEmpety(){
        val mMovie = MutableLiveData<PagedList<MovieEntity>>()
        mMovie.value = PagedTestDataSources.snapshot()

        Mockito.`when`(movieRepository.getFavMovie()).thenReturn(mMovie)

        viewModel.getFavorite().observeForever(movieObserver)
        verify(movieObserver).onChanged(mMovie.value)

        val valueActDataSize = viewModel.getFavorite().value?.size
        Assert.assertTrue("size of data should be 0, actual is $valueActDataSize", valueActDataSize == 0)
    }

    class PagedTestDataSources private constructor(private val items: List<MovieEntity>): PositionalDataSource<MovieEntity>() {
        companion object {
            fun snapshot(items: List<MovieEntity> = listOf()): PagedList<MovieEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(
                params: LoadInitialParams,
                callback: LoadInitialCallback<MovieEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}