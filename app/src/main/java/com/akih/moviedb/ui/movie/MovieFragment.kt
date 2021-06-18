package com.akih.moviedb.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.room.Movie
import com.akih.moviedb.databinding.FragmentMovieBinding
import com.akih.moviedb.ui.detail.movie.DetailMovieActivity
import com.akih.moviedb.utils.Resource
import com.akih.moviedb.utils.Status
import com.akih.moviedb.viewModel.ViewModelFactory

class MovieFragment : Fragment(), MovieAdapterInterface {
    private var _binding : FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieViewModel
    private lateinit var factory : ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        initRecyclerView(viewModel)
    }

    private fun initRecyclerView(data : MovieViewModel){
        var mAdapter = MovieAdapter(this@MovieFragment)
        binding.rvMovie.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = mAdapter
        }

        data.getAllMovie().observe(requireActivity(),{movie ->
            if(movie != null){
                when(movie.status){
                    Status.SUCCESS -> {
                        val movieData = movie.data
                        if (movieData != null) {
                            mAdapter.setList(movieData)
                        }
                        mAdapter.submitList(movie.data)
                        mAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR ->{
                        Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onTap(movie: Movie) {
        startActivity(Intent(activity, DetailMovieActivity::class.java).also {
            it.putExtra(DetailMovieActivity.EXTRA_ID, movie.id)
        })
    }

    override fun onLongTap(movie: Movie) {
        Toast.makeText(context, "You Click On ${movie.title} Movie", Toast.LENGTH_LONG).show()
    }
}