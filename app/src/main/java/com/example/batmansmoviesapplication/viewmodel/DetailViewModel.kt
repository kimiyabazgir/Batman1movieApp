package com.example.batmansmoviesapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.batmansmoviesapplication.models.detail.ResponseDetailMovies
import com.example.batmansmoviesapplication.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor( private val repository: DetailRepository) :ViewModel(){


    val detailMovie = MutableLiveData<ResponseDetailMovies>()
    val loading = MutableLiveData<Boolean>()

    fun loadDetailMovie(apikey:String,i: String) = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.detailMovies(apikey,i)
        if (response.isSuccessful) {
            detailMovie.postValue(response.body())
        }
        loading.postValue(false)
    }
}