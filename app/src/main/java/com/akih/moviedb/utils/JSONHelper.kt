package com.akih.moviedb.utils

import android.content.Context
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.data.source.remote.response.MovieResponse
import com.akih.moviedb.data.source.remote.response.TVShowResponse
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

    fun loadMovie(): List<MovieEntity> {
        val list = ArrayList<MovieEntity>()
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

                val movieResponse = MovieEntity(id, title, year, duration, rating, genre, synopsis, banner, trailer)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTVShow(): List<TVShowEntity> {
        val list = ArrayList<TVShowEntity>()
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

                val tvShowResponse = TVShowEntity(id, title, year, duration, rating, genre, synopsis, banner, trailer)
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadMovieDetail(movieId : Int): MovieEntity {
        val fileName = String.format("movie_%s.json", movieId)
        var list : MovieEntity? = null
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
                list = MovieEntity(id, title, year, duration, rating, genre, synopsis, banner, trailer)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list as MovieEntity
    }

    fun loadTVShowDetail(tvShowId : Int): TVShowEntity {
        val fileName = String.format("tvShow_%s.json", tvShowId)
        var list : TVShowEntity? = null
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
                list = TVShowEntity(id, title, year, duration, rating, genre, synopsis, banner, trailer)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list as TVShowEntity
    }
}