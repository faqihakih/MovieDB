package com.akih.moviedb.ui.favorite.tvShowFavorite

import com.akih.moviedb.data.source.local.entity.TVShowEntity

interface TVShowAdapterInterface {
    fun onTap(tvShow : TVShowEntity)
    fun onLongTap(tvShow : TVShowEntity)
}