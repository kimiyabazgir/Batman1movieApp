package com.example.batmansmoviesapplication.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.batmansmoviesapplication.R
import com.example.batmansmoviesapplication.databinding.FragmentDetailBinding
import com.example.batmansmoviesapplication.databinding.FragmentHomeBinding
import com.example.batmansmoviesapplication.utils.showInvisible
import com.example.batmansmoviesapplication.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var movieID = ""
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieID = args.movieId ?: ""

        viewModel.loadDetailMovie("3e974fca", movieID)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.detailMovie.observe(viewLifecycleOwner) { response ->
                posterBigImg.load(response.Poster)
                posterNormalImg.load(response.Poster) {
                    crossfade(true)
                    crossfade(800)
                }
                movieNameTxt.text = response.Title
                movieRateTxt.text = response.imdbRating
                movieTimeTxt.text = response.Runtime
                movieDateTxt.text = response.Released
                movieActorsInfo.text=response.Actors
                movieSummaryInfo.text=response.Plot
            }
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    detailLoading.showInvisible(true)
                    detailScrollView.showInvisible(false)
                } else {
                    detailLoading.showInvisible(false)
                    detailScrollView.showInvisible(true)
                }
            }
        }

    }
}