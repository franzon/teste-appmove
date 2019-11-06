package com.example.testeappmoove.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testeappmoove.data.entities.MovieResponse
import com.example.testeappmoove.data.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    fun getPopularMovies(): LiveData<MovieResponse> {
        val popularMoviesResponse = MutableLiveData<MovieResponse>()

        MovieApi().getPopularMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // TODO
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                popularMoviesResponse.value = response.body()
            }
        })

        return popularMoviesResponse
    }
}