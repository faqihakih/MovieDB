package com.akih.moviedb.ui.favorite.tvShowFavorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.databinding.ItemCardBinding
import com.bumptech.glide.Glide

class TVShowAdapter(private val tvShowAdapterInterface: TVShowAdapterInterface) : PagedListAdapter<TVShowEntity, TVShowAdapter.TVShowViewHolder>(TVShowAdapter.CALLBACK){

    companion object{
        private val CALLBACK = object : DiffUtil.ItemCallback<TVShowEntity>(){
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TVShowEntity,
                newItem: TVShowEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class TVShowViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow : TVShowEntity){
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


    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val tvShows = getItem(position)
        if (tvShows != null){
            holder.bind(tvShows)
        }
    }

}