package com.example.batmansmoviesapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.batmansmoviesapplication.db.MoviesEntity
import com.example.batmansmoviesapplication.di.NetworkRequest
import com.example.batmansmoviesapplication.di.NetworkResponse
import com.example.batmansmoviesapplication.models.home.ResponseMoviesList
import com.example.batmansmoviesapplication.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository):ViewModel() {

        val popularData = MutableLiveData<NetworkRequest<ResponseMoviesList>>()
        fun callPopularApi() = viewModelScope.launch {
            popularData.value = NetworkRequest.Loading()
            val response = repository.moviesList()
            popularData.value = NetworkResponse(response).generalNetworkResponse()
            //Cache
            val cache = popularData.value?.data
            if (cache != null)
                offlinePopular(cache)
        }
    //Local
    private fun savePopular(entity: MoviesEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveMovies(entity)
    }

    val readPopularFromDb = repository.loadMovies().asLiveData()

    private fun offlinePopular(response: ResponseMoviesList) {
        val entity = MoviesEntity(0, response)
        savePopular(entity)
    }
    }

