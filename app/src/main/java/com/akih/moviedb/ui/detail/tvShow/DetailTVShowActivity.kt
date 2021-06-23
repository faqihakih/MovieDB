package com.akih.moviedb.ui.detail.tvShow

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.ToggleButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.akih.moviedb.R
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.databinding.ActivityDetailTVShowBinding
import com.akih.moviedb.vo.Resource
import com.akih.moviedb.vo.Status
import com.akih.moviedb.viewModel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailTVShowActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailTVShowBinding
    private lateinit var viewModel: DetailTVShowViewModel
    private lateinit var factory: ViewModelFactory
    private var setFavorite : ToggleButton? = null

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTVShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        getId()
        observe()
        binding.toggleButton.setOnClickListener(this)
    }

    private fun initViewModel(){
        factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTVShowViewModel::class.java]
    }

    private fun observe(){
        viewModel.getTVShow.observe(this, Observer { watchTrailer(it) })
    }

    private fun getId(){
        val id = intent.extras
        if (id != null){
            val data = id.getInt(DetailTVShowActivity.EXTRA_ID, 0)
            if(data != null){
                viewModel.setSelectedTVShow(data)
                viewModel.getTVShow.observe(this, {tv ->
                    if(tv != null){
                        when(tv.status){
                            Status.SUCCESS -> if(tv.data != null){
                                initView(tv.data)
                                if(tv.data.favorite == true){
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
                })
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
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(itemData.data?.trailer)))
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.toggleButton ->{
                viewModel.getTVShow.observe(this, {tv ->
                    if (tv != null) {
                        when (tv.status) {
                            Status.SUCCESS -> if (tv.data != null) {
                                if(tv.data.favorite == false){
                                    tv.data.favorite = true
                                    setFavorit(tv.data.favorite)
                                }else{
                                    tv.data.favorite = false
                                    setFavorit(tv.data.favorite)
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
        }
    }
}