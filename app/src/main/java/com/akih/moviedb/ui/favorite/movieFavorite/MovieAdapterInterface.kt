package com.akih.moviedb.ui.favorite.movieFavorite

import com.akih.moviedb.data.source.local.entity.MovieEntity

interface MovieAdapterInterface {
    fun onTap(movie: MovieEntity)
    fun onLongTap(movie: MovieEntity)
}