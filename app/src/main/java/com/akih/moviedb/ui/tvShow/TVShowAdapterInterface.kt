package com.akih.moviedb.ui.tvShow

import com.akih.moviedb.data.source.local.entity.TVShowEntity

interface TVShowAdapterInterface {
    fun onTap(tvShow : TVShowEntity)
    fun onLongTap(tvShow : TVShowEntity)
}