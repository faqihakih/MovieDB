package com.akih.moviedb.ui.tvShow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.akih.moviedb.data.source.local.entity.TVShowEntity
import com.akih.moviedb.databinding.FragmentTVShowBinding
import com.akih.moviedb.ui.detail.tvShow.DetailTVShowActivity
import com.akih.moviedb.viewModel.ViewModelFactory
import com.akih.moviedb.vo.Status

class TVShowFragment : Fragment(), TVShowAdapterInterface {
    private var _binding : FragmentTVShowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TVShowViewModel
    private lateinit var factory : ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTVShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]
        initRecyclerView(viewModel)
    }
    private fun initRecyclerView(data : TVShowViewModel){
        var mAdapter = TVShowAdapter(this@TVShowFragment)
        binding.rvTvShow.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = mAdapter
        }

        data.getAllTVShow().observe(requireActivity(),{movie ->
            if(movie != null){
                when(movie.status){
                    Status.SUCCESS -> {
                        val movieData = movie.data
                        if (movieData != null) {
                            mAdapter.submitList(movieData)
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

    override fun onTap(tvShow: TVShowEntity) {
        startActivity(Intent(activity, DetailTVShowActivity::class.java).also {
            it.putExtra(DetailTVShowActivity.EXTRA_ID, tvShow.id)
        })
    }

    override fun onLongTap(tvShow: TVShowEntity) {
        Toast.makeText(context, "You Click On ${tvShow.title} TV Show", Toast.LENGTH_LONG).show()
    }
}