package com.akih.moviedb.utils

import android.content.Context
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.room.Movie
import com.akih.moviedb.data.source.local.room.TVShow
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JSONHelper(private val context: Context) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovie(): List<Movie> {
        val list = ArrayList<Movie>()
        try {
            val responseObject = JSONObject(parsingFileToString("movies.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getInt("id")
                val title = movie.getString("title")
                val year = movie.getString("year")
                val duration = movie.getString("duration")
                val rating = movie.getString("rating")
                val genre = movie.getString("genre")
                val synopsis = movie.getString("synopsis")
                val banner = movie.getString("banner")
                val trailer = movie.getString("trailer")

                val movieResponse = Movie(id, title, year, duration, rating, genre, synopsis, banner, trailer)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTVShow(): List<TVShow> {
        val list = ArrayList<TVShow>()
        try {
            val responseObject = JSONObject(parsingFileToString("tvShows.json").toString())
            val listArray = responseObject.getJSONArray("tvShows")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getInt("id")
                val title = movie.getString("title")
                val year = movie.getString("year")
                val duration = movie.getString("duration")
                val rating = movie.getString("rating")
                val genre = movie.getString("genre")
                val synopsis = movie.getString("synopsis")
                val banner = movie.getString("banner")
                val trailer = movie.getString("trailer")

                val tvShowResponse = TVShow(id, title, year, duration, rating, genre, synopsis, banner, trailer)
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadMovieDetail(movieId : Int): Movie {
        val fileName = String.format("movie_%s.json", movieId)
        var list : Movie? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val movie = JSONObject(result)
                val id = movie.getInt("id")
                val title = movie.getString("title")
                val year = movie.getString("year")
                val duration = movie.getString("duration")
                val rating = movie.getString("rating")
                val genre = movie.getString("genre")
                val synopsis = movie.getString("synopsis")
                val banner = movie.getString("banner")
                val trailer = movie.getString("trailer")
                list = Movie(id, title, year, duration, rating, genre, synopsis, banner, trailer)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list as Movie
    }

    fun loadTVShowDetail(tvShowId : Int): TVShow {
        val fileName = String.format("tvShow_%s.json", tvShowId)
        var list : TVShow? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val movie = JSONObject(result)
                val id = movie.getInt("id")
                val title = movie.getString("title")
                val year = movie.getString("year")
                val duration = movie.getString("duration")
                val rating = movie.getString("rating")
                val genre = movie.getString("genre")
                val synopsis = movie.getString("synopsis")
                val banner = movie.getString("banner")
                val trailer = movie.getString("trailer")
                list = TVShow(id, title, year, duration, rating, genre, synopsis, banner, trailer)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list as TVShow
    }
}