package com.akih.moviedb.ui.tvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akih.moviedb.data.source.local.room.Movie
import com.akih.moviedb.data.source.local.room.TVShow
import com.akih.moviedb.databinding.ItemCardBinding
import com.akih.moviedb.ui.movie.MovieAdapter
import com.bumptech.glide.Glide

class TVShowAdapter(private val tvShowAdapterInterface: TVShowAdapterInterface) : PagedListAdapter<TVShow, TVShowAdapter.TVShowViewHolder>(TVShowAdapter.CALLBACK){

    companion object{
        private val CALLBACK = object : DiffUtil.ItemCallback<TVShow>(){
            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TVShow,
                newItem: TVShow
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    private val tvShows = ArrayList<TVShow>()
    fun setList(mTVShow : List<TVShow>){
        this.tvShows.clear()
        this.tvShows.addAll(mTVShow)
        notifyDataSetChanged()
    }

    inner class TVShowViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow : TVShow){
            binding.root.setOnClickListener { tvShowAdapterInterface.onTap(tvShow) }
            binding.root.setOnLongClickListener {
                tvShowAdapterInterface.onLongTap(tvShow)
                return@setOnLongClickListener true
            }
            Glide.with(binding.root).load(tvShow.banner).into(binding.ivBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val view = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(view)
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) = holder.bind(tvShows[position])

}