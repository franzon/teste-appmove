package com.example.testeappmoove.data

data class Movie(
    val id: Int,
    val poster_path: String,
    val backdrop_path: String,
    val title: String,
    val overview: String,
    var liked: Boolean
)

data class MovieResponse(
    val results: List<Movie>
)