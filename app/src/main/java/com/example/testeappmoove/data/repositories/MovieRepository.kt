package com.example.testeappmoove.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testeappmoove.data.dao.LikeDao
import com.example.testeappmoove.data.database.AppDatabase
import com.example.testeappmoove.data.entities.LikedMovie
import com.example.testeappmoove.data.entities.MovieDetails
import com.example.testeappmoove.data.entities.MovieResponse
import com.example.testeappmoove.data.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val likeDao: LikeDao) {

    // TODO: utilizar dependency injection
    val api = MovieApi()

    fun getPopularMovies(): LiveData<MovieResponse> {
        val popularMoviesResponse = MutableLiveData<MovieResponse>()

        api.getPopularMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                val likes = likeDao.all()
                val likedIds = likes.map { it.id }

                val body = response.body()

                for (movie in body?.results!!) {
                    movie.liked = likedIds.contains(movie.id)
                }

                popularMoviesResponse.value = response.body()
            }
        })

        return popularMoviesResponse
    }

    fun getMovieDetails(movieId: Int): LiveData<MovieDetails> {

        val movieDetailsResponse = MutableLiveData<MovieDetails>()

        api.getMovieDetails(movieId).enqueue(object : Callback<MovieDetails> {
            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {

                val likes = likeDao.all()
                val likedIds = likes.map { it.id }

                val body = response.body()
                body?.liked = likedIds.contains(body?.id)

                movieDetailsResponse.value = response.body()
            }
        })

        return movieDetailsResponse
    }

    fun searchMovies(query: String): LiveData<MovieResponse> {
        val moviesResponse = MutableLiveData<MovieResponse>()

        api.searchMovies(query).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                moviesResponse.value = response.body()
            }
        })

        return moviesResponse
    }

    fun likeMovie(movieId: Int) {
        val likes = likeDao.all()
        val likedIds = likes.map { it.id }

        if (likedIds.contains(movieId)) {
            likeDao.delete(LikedMovie(movieId))
        } else {
            likeDao.insert(LikedMovie(movieId))
        }
    }
}