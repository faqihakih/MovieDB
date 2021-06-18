package com.akih.moviedb.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse (
    val id : Int = 0,
    val title : String,
    val year : String,
    val duration : String,
    val rating : String,
    val genre : String,
    val synopsis : String,
    val banner : String,
    val trailer : String
) :Parcelable