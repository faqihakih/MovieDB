package com.akih.moviedb.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movieDB: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertShow(movieDB: List<TVShow>)

    @Delete
    fun deleteMovie(movieDB: Movie)

    @Delete
    fun deleteShow(movieDB: TVShow)

    @Update
    fun updateMovie(movieDB: Movie)

    @Update
    fun updateShow(movieDB: TVShow)

    @Query("SELECT * FROM movie ORDER BY id ASC")
    fun getAllMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM tvshow ORDER BY id ASC")
    fun getAllShow(): DataSource.Factory<Int, TVShow>

    @Query("SELECT * FROM movie WHERE id=:id")
    fun getMovieById(id:Int):LiveData<Movie>

    @Query("SELECT * FROM tvshow WHERE id=:id")
    fun getShowById(id:Int):LiveData<TVShow>

    @Query("SELECT * FROM movie WHERE favorite=1")
    fun getFavMovie():DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM tvshow WHERE favorite=1")
    fun getFaShow():DataSource.Factory<Int, TVShow>

}