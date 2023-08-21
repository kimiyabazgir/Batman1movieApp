package com.example.batmansmoviesapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batmansmoviesapplication.R
import com.example.batmansmoviesapplication.databinding.FragmentHomeBinding
import com.example.batmansmoviesapplication.ui.home.adapter.MoviesListAdapter
import com.example.batmansmoviesapplication.utils.initRecycler
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

        viewModel.moviesList()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            viewModel.moviesListLiveData.observe(viewLifecycleOwner) {
                moviesListAdapter.differ.submitList(it.search) {

                    //RecyclerView
                    moviesRecycler.initRecycler(
                        LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL, false
                        ), moviesListAdapter)
                }
            }
        }

        moviesListAdapter.setOnItemClickListener {

            val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(direction)
        }

    }
}

