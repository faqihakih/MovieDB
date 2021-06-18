package com.akih.moviedb.ui.tvShow

import com.akih.moviedb.data.source.local.room.TVShow

interface TVShowAdapterInterface {
    fun onTap(tvShow : TVShow)
    fun onLongTap(tvShow : TVShow)
}