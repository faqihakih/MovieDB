package com.akih.moviedb.ui.favorite.tvShowFavorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.databinding.FragmentFavoriteTVShowBinding
import com.akih.moviedb.ui.detail.tvShow.DetailTVShowActivity
import com.akih.moviedb.viewModel.ViewModelFactory

class FavoriteTVShowFragment : Fragment(), TVShowAdapterInterface {
    private var _binding: FragmentFavoriteTVShowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoriteTVShowViewModel
    private lateinit var factory : ViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteTVShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteTVShowViewModel::class.java]
        var adapterShow = TVShowAdapter(this@FavoriteTVShowFragment)
        viewModel.getFavorit().observe(viewLifecycleOwner, {movie ->
            adapterShow.submitList(movie)
            adapterShow.notifyDataSetChanged()
        })

        binding.rvTvShow.apply {
            setHasFixedSize(true)
            adapter = adapterShow
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onTap(tvShow: TVShowEntity) {
        startActivity(Intent(activity, DetailTVShowActivity::class.java).also {
            it.putExtra(DetailTVShowActivity.EXTRA_ID, tvShow.id)
        })
    }

    override fun onLongTap(tvShow: TVShowEntity) {
        Toast.makeText(context, "You Click On ${tvShow.title} TV Show", Toast.LENGTH_LONG).show()
    }
}