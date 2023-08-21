package com.example.batmansmoviesapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.batmansmoviesapplication.R
import com.example.batmansmoviesapplication.databinding.FragmentHomeBinding
import com.example.batmansmoviesapplication.di.NetworkRequest
import com.example.batmansmoviesapplication.models.home.ResponseMoviesList
import com.example.batmansmoviesapplication.ui.home.adapter.MoviesListAdapter
import com.example.batmansmoviesapplication.utils.Constants
import com.example.batmansmoviesapplication.utils.initRecycler
import com.example.batmansmoviesapplication.utils.onceObserve
import com.example.batmansmoviesapplication.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var moviesListAdapter: MoviesListAdapter

    private val viewModel: HomeViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callPopularData()
        loadPopularData()



        moviesListAdapter.setOnItemClickListener {

            val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(direction)
        }

    }

    //---Popular---//
    private fun callPopularData() {
        initPopularRecycler()
        viewModel.readPopularFromDb.onceObserve(viewLifecycleOwner) { database ->
            if (database.isNotEmpty()) {
                database[0].response.search?.let { result ->
                    fillPopularAdapter(result.toMutableList())
                }
            } else {
                viewModel.callPopularApi()
            }
        }
    }

    private fun loadPopularData() {
        binding.apply {
            viewModel.popularData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {

                    }
                    is NetworkRequest.Success -> {

                        response.data?.let { data ->
                            if (data.Response.isNotEmpty()) {
                                fillPopularAdapter(data.search.toMutableList())
                            }
                        }
                    }
                    is NetworkRequest.Error -> {
                       // binding.root.showSnackBar(response.message!!)
                    }
                }
            }
        }
    }

    private fun fillPopularAdapter(result: MutableList<ResponseMoviesList.Search>) {
        moviesListAdapter.differ.submitList(result)

    }

    private fun initPopularRecycler() {
        binding.moviesRecycler.initRecycler(
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL, false
                ), moviesListAdapter)

    }



}

