package com.akih.moviedb.ui.favorite.movieFavorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.databinding.ItemCardBinding
import com.bumptech.glide.Glide

class MovieAdapter(private val movieAdapterInterface: MovieAdapterInterface) : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(CALLBACK){
    companion object{
        private val CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
    inner class MovieViewHolder(private val binding : ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity){
            binding.root.setOnClickListener { movieAdapterInterface.onTap(movie) }
            binding.root.setOnLongClickListener {
                movieAdapterInterface.onLongTap(movie)
                return@setOnLongClickListener true
            }
            Glide.with(binding.root).load(movie.banner).into(binding.ivBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null){
            holder.bind(movies)
        }
    }

    private var movieList = ArrayList<MovieEntity>()

    fun setValueMovie(movie:List<MovieEntity>){
        if(movie != null)return
        this.movieList.clear()
        this.movieList.addAll(movie)
    }
}