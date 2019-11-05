package com.example.testeappmoove.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie (
    val id: Int,
    val poster_path: String,
    val backdrop_path: String,
    val title: String,
    val overview: String
)

@JsonClass(generateAdapter = true)
data class MovieResponse (
    val results: List<Movie>
)