package com.example.testeappmoove.data.entities

data class Genre(
    val id: Int,
    val name: String
)

data class MovieDetails(
    val id: Int,
    val poster_path: String,
    val backdrop_path: String,
    val title: String,
    val overview: String,
    var liked: Boolean,
    var vote_average: Double,
    var original_language: String,
    var release_date: String,
    var genres: List<Genre>
)