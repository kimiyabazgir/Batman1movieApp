package com.example.batmansmoviesapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.batmansmoviesapplication.models.home.ResponseMoviesList
import com.example.batmansmoviesapplication.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository):ViewModel() {
    val moviesListLiveData = MutableLiveData<ResponseMoviesList>()

    fun moviesList() = viewModelScope.launch {
        val response = repository.moviesList()
        if (response.isSuccessful) {
            moviesListLiveData.postValue(response.body())

        }


    }
}