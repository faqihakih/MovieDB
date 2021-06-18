package com.akih.moviedb.data.source.local.entity

data class MovieEntity(
    val id : Int = 0,
    val title : String,
    val year : String,
    val duration : String,
    val rating : String,
    val genre : String,
    val synopsis : String,
    val banner : String,
    val trailer : String
)
