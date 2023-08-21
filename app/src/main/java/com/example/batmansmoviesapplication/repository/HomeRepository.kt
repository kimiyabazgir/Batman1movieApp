package com.example.batmansmoviesapplication.repository

import com.example.batmansmoviesapplication.api.ApiServices
import com.example.batmansmoviesapplication.db.MoviesDao
import com.example.batmansmoviesapplication.db.MoviesEntity
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api:ApiServices,private val dao: MoviesDao) {

    suspend fun moviesList()=api.moviesList()

    suspend fun saveMovies(entity: MoviesEntity) = dao.saveMovies(entity)
    fun loadMovies() = dao.loadMovies()

}