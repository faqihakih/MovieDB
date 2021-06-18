package com.akih.moviedb.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.room.Movie
import com.akih.moviedb.databinding.ItemCardBinding
import com.bumptech.glide.Glide

class MovieAdapter(private val movieAdapterInterface: MovieAdapterInterface) : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(CALLBACK){
    companion object{
        private val CALLBACK = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
    private val movies = ArrayList<Movie>()
    fun setList(mMovie: List<Movie>){
        this.movies.clear()
        this.movies.addAll(mMovie)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding : ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie){
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

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(movies[position])
}