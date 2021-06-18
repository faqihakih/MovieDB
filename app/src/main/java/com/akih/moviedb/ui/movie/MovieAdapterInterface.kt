package com.akih.moviedb.ui.movie

import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.room.Movie

interface MovieAdapterInterface {
    fun onTap(movie: Movie)
    fun onLongTap(movie: Movie)
}