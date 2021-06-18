package com.akih.moviedb.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movieDB: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertShow(movieDB: List<TVShowEntity>)

    @Delete
    fun deleteMovie(movieDB: MovieEntity)

    @Delete
    fun deleteShow(movieDB: TVShowEntity)

    @Update
    fun updateMovie(movieDB: MovieEntity)

    @Update
    fun updateShow(movieDB: TVShowEntity)

    @Query("SELECT * FROM movie ORDER BY id ASC")
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshow ORDER BY id ASC")
    fun getAllShow(): DataSource.Factory<Int, TVShowEntity>

    @Query("SELECT * FROM movie WHERE id=:id")
    fun getMovieById(id:Int):LiveData<MovieEntity>

    @Query("SELECT * FROM tvshow WHERE id=:id")
    fun getShowById(id:Int):LiveData<TVShowEntity>

    @Query("SELECT * FROM movie WHERE favorite=1")
    fun getFavMovie():DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshow WHERE favorite=1")
    fun getFaShow():DataSource.Factory<Int, TVShowEntity>

}