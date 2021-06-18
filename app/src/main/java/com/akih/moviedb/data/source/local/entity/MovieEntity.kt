package com.akih.moviedb.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Movie")
@Parcelize
data class MovieEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id : Int = 0,

        @ColumnInfo(name = "title")
        var title : String,

        @ColumnInfo(name = "year")
        var year : String,

        @ColumnInfo(name = "duration")
        var duration : String,

        @ColumnInfo(name = "rating")
        var rating : String,

        @ColumnInfo(name = "ganre")
        var genre : String,

        @ColumnInfo(name = "synopsis")
        var synopsis : String,

        @ColumnInfo(name = "banner")
        var banner : String,

        @ColumnInfo(name = "trailer")
        var trailer : String,

        @ColumnInfo(name = "favorite")
        var favorite : Boolean = false
) : Parcelable