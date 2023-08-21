package com.example.batmansmoviesapplication.db


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.batmansmoviesapplication.models.home.ResponseMoviesList
import com.example.batmansmoviesapplication.utils.Constants.Movies_DB_TABLE

@Entity(tableName = Movies_DB_TABLE)
data class MoviesEntity(
    @PrimaryKey(autoGenerate = false)
    var iD: Int=0 ,
    var response: ResponseMoviesList
)