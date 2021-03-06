package com.akih.moviedb.ui.movie

import com.akih.moviedb.data.source.local.entity.MovieEntity

interface MovieAdapterInterface {
    fun onTap(movie: MovieEntity)
    fun onLongTap(movie: MovieEntity)
}