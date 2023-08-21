package com.example.batmansmoviesapplication.db


import androidx.room.TypeConverter
import com.example.batmansmoviesapplication.models.home.ResponseMoviesList
import com.google.gson.Gson

class MoviesTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun recipeToJson(recipe: ResponseMoviesList): String {
        return gson.toJson(recipe)
    }

    @TypeConverter
    fun stringToRecipe(data: String): ResponseMoviesList {
        return gson.fromJson(data, ResponseMoviesList::class.java)
    }



}