package com.akih.moviedb.ui.detail.tvShow

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.ToggleButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.databinding.ActivityDetailTVShowBinding
import com.akih.moviedb.ui.detail.movie.DetailMovieActivity
import com.akih.moviedb.vo.Resource
import com.akih.moviedb.vo.Status
import com.akih.moviedb.viewModel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailTVShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTVShowBinding
    private lateinit var viewModel: DetailTVShowViewModel
    private lateinit var factory: ViewModelFactory
    private lateinit var tvShowEntity: TVShowEntity
    private var setFavorite : ToggleButton? = null
    private var stateFavorite: Boolean = false

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTVShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        getId()
        setFavoriteOnClick()
        observe()
    }

    private fun initViewModel(){
        factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTVShowViewModel::class.java]
    }

    private fun observe(){
        viewModel.getTVShow.observe(this, Observer { setFav(it) })
        viewModel.getTVShow.observe(this, Observer { watchTrailer(it) })
    }

    private fun getId(){
        val id = intent.extras
        if (id != null){
            val data = id.getInt(DetailMovieActivity.EXTRA_ID, 0)
            viewModel.setSelectedTVShow(data)
        }
    }

    private fun setFav(movie: Resource<TVShowEntity>) {
        if(movie != null){
            when(movie.status){
                Status.SUCCESS -> if(movie.data != null){
                    tvShowEntity = movie.data
                    initView(tvShowEntity)
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

    @SuppressLint("SetTextI18n")
    private fun initView(itemData: TVShowEntity){
        Glide.with(this.applicationContext).load(itemData.banner).into(binding.ivBanner)
        binding.apply {
            tvTitle.text = "${itemData.title} - (${itemData.year})"
            tvDuration.text = itemData.duration
            tvRating.text = itemData.rating
            tvGenre.text = itemData.genre
            tvSynopsis.text = itemData.synopsis
        }
    }

    private fun setFavoriteOnClick(){
        viewModel.getTVShow.observe(this, {movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.SUCCESS -> if (movie.data != null) {
                        if(movie.data.favorite == false){
                            setFavorit(true)
                        }else{
                            setFavorit(false)
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
        viewModel.setFavoritShow()
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

    private fun watchTrailer(itemData: Resource<TVShowEntity>){
        binding.btnTrailer.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(tvShowEntity.trailer)))
        }
    }
}