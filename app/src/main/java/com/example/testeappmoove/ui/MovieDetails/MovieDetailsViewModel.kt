package com.example.testeappmoove.ui.MovieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testeappmoove.data.entities.MovieDetails
import com.example.testeappmoove.data.repositories.MovieRepository

class MovieDetailsViewModel(private val movieId: Int) : ViewModel() {

    fun getMovie() = MovieRepository().getMovieDetails(movieId)
}

// Utilizando Factory para passar o id do filme como parâmetro do ViewModel
// https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/#7

class MovieDetailsViewModelFactory(private val movieId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(movieId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}