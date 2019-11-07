package com.example.testeappmoove.ui.PopularMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testeappmoove.data.repositories.MovieRepository

class PopularMoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getPopularMovies() = movieRepository.getPopularMovies()

    fun likeMovie(movieId: Int) = movieRepository.likeMovie(movieId)
}

class PopularMoviesViewModelFactory(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularMoviesViewModel::class.java)) {
            return PopularMoviesViewModel(movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}