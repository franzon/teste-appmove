package com.example.testeappmoove.ui.MovieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testeappmoove.data.repositories.MovieRepository

class MovieDetailsViewModel(
    private val movieId: Int,
    private val movieRepository: MovieRepository
) : ViewModel() {

    val liked = MutableLiveData<Boolean>(false)

    fun getMovie() = movieRepository
        .getMovieDetails(movieId)

    fun likeMovie(movieId: Int) = movieRepository.likeMovie(movieId)
}

// Utilizando Factory para passar o id do filme como parâmetro do ViewModel
// https://codelabs.developers.google.com/codelabs/kotlin-android-training-view-model/#7

class MovieDetailsViewModelFactory(
    private val movieId: Int,
    private val movieRepository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(movieId, movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}