package com.example.testeappmoove.service

import com.example.testeappmoove.data.Movie
import com.example.testeappmoove.data.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApi {
    @GET("movie/popular")
    fun getPopularMovies(): Deferred<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int): Deferred<Response<Movie>>
}