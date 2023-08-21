package com.example.batmansmoviesapplication.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.batmansmoviesapplication.databinding.ItemHomeMoviesBinding
import com.example.batmansmoviesapplication.models.home.ResponseMoviesList
import javax.inject.Inject


class MoviesListAdapter @Inject constructor() : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {
    private lateinit var binding: ItemHomeMoviesBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesListAdapter.ViewHolder {
        binding = ItemHomeMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: MoviesListAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: ResponseMoviesList.Search) {

            binding.apply {
                movieTitleTxt.text = item.Title
                movieimdbIDTxt.text = item.imdbID
                movieTypeTxt.text = item.Type
                movieYearTxt.text = item.Year
                moviePosterImg.load(item.Poster) {
                    crossfade(true)
                    crossfade(800)

                }
                }
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(item.imdbID)
                }
            }
            }
        }
    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

        private val differCallback = object : DiffUtil.ItemCallback<ResponseMoviesList.Search>() {

            override fun areItemsTheSame(
                oldItem: ResponseMoviesList.Search,
                newItem: ResponseMoviesList.Search
            ): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(
                oldItem: ResponseMoviesList.Search,
                newItem: ResponseMoviesList.Search
            ): Boolean {
                return oldItem == newItem
            }

        }
        val differ= AsyncListDiffer(this,differCallback)
    }