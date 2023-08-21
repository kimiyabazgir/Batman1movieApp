package com.example.batmansmoviesapplication.repository

import com.example.batmansmoviesapplication.api.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api:ApiServices) {

    suspend fun moviesList()=api.moviesList()

}