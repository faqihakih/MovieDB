package com.akih.moviedb.ui.favorite.movieFavorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.databinding.FragmentFavoriteMovieBinding
import com.akih.moviedb.ui.detail.movie.DetailMovieActivity
import com.akih.moviedb.viewModel.ViewModelFactory

class FavoriteMovieFragment : Fragment(), MovieAdapterInterface {
    private var _binding:FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoriteMovieViewModel
    private lateinit var factory : ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
        var adapterMovie = MovieAdapter(this@FavoriteMovieFragment)
        viewModel.getFavorite().observe(viewLifecycleOwner, {movie ->
            adapterMovie.setValueMovie(movie)
            adapterMovie.submitList(movie)
            adapterMovie.notifyDataSetChanged()
        })

        binding.rvMovie.apply {
            setHasFixedSize(true)
            adapter = adapterMovie
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onTap(movie: MovieEntity) {
        startActivity(Intent(activity, DetailMovieActivity::class.java).also {
            it.putExtra(DetailMovieActivity.EXTRA_ID, movie.id)
        })
    }

    override fun onLongTap(movie: MovieEntity) {
        Toast.makeText(context, "You Click On ${movie.title} Movie", Toast.LENGTH_LONG).show()
    }
}