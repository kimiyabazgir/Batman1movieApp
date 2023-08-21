package com.example.batmansmoviesapplication.models.home

import com.google.gson.annotations.SerializedName

data class ResponseMoviesList(
    @SerializedName("Response")
    val Response: String,
    @SerializedName("Search")
    val search: List<Search>,
    @SerializedName("totalResults")
    val totalResults: String
)
{
    data class Search(
        @SerializedName("Poster")
        val Poster: String,
        @SerializedName("Title")
        val Title: String,
        @SerializedName("Type")
        val Type: String,
        @SerializedName("Year")
        val Year: String,
        @SerializedName("imdbID")
        val imdbID: String
    )
}