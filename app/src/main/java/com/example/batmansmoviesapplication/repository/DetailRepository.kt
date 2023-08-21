package com.example.batmansmoviesapplication.repository

import com.example.batmansmoviesapplication.api.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api:ApiServices) {

    suspend fun detailMovies(apikey:String,i: String)=api.detailMovies(apikey,i)
}