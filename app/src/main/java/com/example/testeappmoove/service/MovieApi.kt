package com.example.testeappmoove.service

import com.example.testeappmoove.data.Movie
import com.example.testeappmoove.data.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovies(): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int): Call<Response<Movie>>
}