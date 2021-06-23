package com.akih.moviedb.ui.detail.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.akih.moviedb.R
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.databinding.ActivityDetailMovieBinding
import com.akih.moviedb.vo.Resource
import com.akih.moviedb.vo.Status
import com.akih.moviedb.viewModel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailMovieActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var movieViewModel: DetailMovieViewModel
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var factory : ViewModelFactory
    private var setFavorite : ToggleButton? = null

    companion object{
        const val EXTRA_ID = "extra_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        getId()
        observe()
        binding.toggleButton.setOnClickListener(this)
    }

    private fun initViewModel(){
        factory = ViewModelFactory.getInstance(this)
        movieViewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
    }
    private fun getId(){
        val id = intent.extras
        if (id != null){
            val data = id.getInt(DetailMovieActivity.EXTRA_ID, 0)
            if (data != null){
                movieViewModel.setSelectedMovie(data)
                movieViewModel.getMovie.observe(this, { dataMovie ->
                    if (dataMovie != null) {
                        when(dataMovie.status) {
                            Status.SUCCESS -> if (dataMovie.data != null) {
                                initView(dataMovie.data)
                                if(dataMovie.data.favorite == true){
                                    setFavorite = binding.toggleButton
                                    setFavorite!!.isChecked = true
                                }else{
                                    setFavorite = binding.toggleButton
                                    setFavorite!!.isChecked = false
                                }
                            }
                            Status.ERROR -> {
                                Log.d("moviesDetail", dataMovie.toString())
                                Toast.makeText(applicationContext, "Maaf, Ada Error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView(itemData: MovieEntity){
        Glide.with(this.applicationContext).load(itemData.banner).into(binding.ivBanner)
        binding.apply {
            tvTitle.text = "${itemData.title} - (${itemData.year})"
            tvDuration.text = itemData.duration
            tvRating.text = itemData.rating
            tvGenre.text = itemData.genre
            tvSynopsis.text = itemData.synopsis
        }
    }

    private fun observe(){
        movieViewModel.getMovie.observe(this, Observer { watchTrailer(it) })
    }


    private fun setFavorit(state: Boolean){
        if (setFavorite == null) return
        val menuItem = binding.toggleButton
        if (!state){
            menuItem.isChecked = true
        }else {
            menuItem.isChecked = false
        }
    }

    private fun watchTrailer(itemData: Resource<MovieEntity>){
        binding.btnTrailer.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(itemData.data?.trailer)))
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.toggleButton -> {
                movieViewModel.getMovie.observe(this, {movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.SUCCESS -> if (movie.data != null) {
                                if(movie.data.favorite == false){
                                    movie.data.favorite = true
                                    setFavorit(movie.data.favorite)
                                }else{
                                    movie.data.favorite = false
                                    setFavorit(movie.data.favorite)
                                }
                            }
                            Status.ERROR -> {
                                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                })
                movieViewModel.setFavoritMovie()
            }
        }
    }
}