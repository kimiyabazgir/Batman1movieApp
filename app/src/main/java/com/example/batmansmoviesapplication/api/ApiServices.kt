package com.example.batmansmoviesapplication.api

import com.example.batmansmoviesapplication.models.detail.ResponseDetailMovies
import com.example.batmansmoviesapplication.models.home.ResponseMoviesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET(".")
    suspend fun moviesList(@Query("apikey") apikey:String="3e974fca",@Query("s") name: String="batman") : Response<ResponseMoviesList>

    @GET(".")
    suspend fun detailMovies(@Query("apikey") apikey:String,@Query("i") i: String) :Response<ResponseDetailMovies>
}