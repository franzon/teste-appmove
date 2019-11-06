package com.example.testeappmoove.data.entities

data class MovieDetails(
    val id: Int,
    val poster_path: String,
    val backdrop_path: String,
    val title: String,
    val overview: String,
    var liked: Boolean
)