package com.akih.moviedb.ui.favorite

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.akih.moviedb.R
import com.akih.moviedb.ui.favorite.movieFavorite.FavoriteMovieFragment
import com.akih.moviedb.ui.favorite.tvShowFavorite.FavoriteTVShowFragment
import com.akih.moviedb.ui.movie.MovieFragment
import com.akih.moviedb.ui.tvShow.TVShowFragment

class FavoriteViewPagerAdapter(private val context: Context, fragmentManager: FragmentManager, data : Bundle) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private val fragmentBundle : Bundle = data
    @StringRes
    private val title = intArrayOf(R.string.movieTitle, R.string.tvShowTitle)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        val fragment = if (position == 0) {
            FavoriteMovieFragment()
        } else {
            FavoriteTVShowFragment()
        }
        fragment.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(title[position])
    }
}