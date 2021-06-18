package com.akih.moviedb.ui.detail.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.ToggleButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.akih.moviedb.data.source.local.room.Movie
import com.akih.moviedb.databinding.ActivityDetailMovieBinding
import com.akih.moviedb.utils.Resource
import com.akih.moviedb.utils.Status
import com.akih.moviedb.viewModel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var movieViewModel: DetailMovieViewModel
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var factory : ViewModelFactory
    private lateinit var movieEntity: Movie
    private var setFavorite : ToggleButton? = null
    private var stateFavorite: Boolean = false

    companion object{
        const val EXTRA_ID = "extra_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        getId()
        setFavoriteOnClick()
        observe()
    }

    private fun initViewModel(){
        factory = ViewModelFactory.getInstance(this)
        movieViewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
    }
    private fun getId(){
        val id = intent.extras
        if (id != null){
            val data = id.getInt(EXTRA_ID, 0)
            movieViewModel.setSelectedMovie(data)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView(itemData: Movie){
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
        movieViewModel.getMovie.observe(this, Observer { setFav(it) })
        movieViewModel.getMovie.observe(this, Observer { watchTrailer(it) })
    }

    private fun setFav(movie: Resource<Movie>) {
        if(movie != null){
            when(movie.status){
                Status.SUCCESS -> if(movie.data != null){
                    movieEntity = movie.data
                    initView(movieEntity)
                    if(movie.data.favorite == true){
                        setFavorite = binding.toggleButton
                        setFavorite!!.isChecked = true
                    }else{
                        setFavorite = binding.toggleButton
                        setFavorite!!.isChecked = false
                    }
                }
                Status.ERROR ->{
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setFavoriteOnClick(){
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

    private fun setFavorit(stateFavorit: Boolean) {
        if(setFavorite == null)return
        val click = binding.toggleButton
        if(!stateFavorit){
            click.isChecked = true
        }else{
            click.isChecked = false
        }
    }

    private fun watchTrailer(itemData: Resource<Movie>){
        binding.btnTrailer.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(movieEntity.trailer)))
        }
    }
}