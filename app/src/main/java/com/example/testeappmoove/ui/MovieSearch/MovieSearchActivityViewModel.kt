package com.example.testeappmoove.ui.MovieSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testeappmoove.data.repositories.MovieRepository

class MovieSearchActivityViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun searchMovies(query: String) = movieRepository.searchMovies(query)
}


class MovieSearchActivityViewModelFactory(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieSearchActivityViewModel::class.java)) {
            return MovieSearchActivityViewModel(movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}