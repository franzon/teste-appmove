package com.example.testeappmoove.ui.PopularMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testeappmoove.data.repositories.MovieRepository

class PopularMoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val popularMovies = movieRepository.popularMovies

    init {
        movieRepository.loadPopularMovies()
    }

    fun likeMovie(movieId: Int) = movieRepository.likeMovie(movieId)

    fun loadPopularMovies() = movieRepository.loadPopularMovies()
    fun loadMoreMovies(page: Int) = movieRepository.loadMoreMovies(page)
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