package com.example.testeappmoove.ui.PopularMovies

import androidx.lifecycle.ViewModel
import com.example.testeappmoove.data.repositories.MovieRepository

class PopularMoviesViewModel : ViewModel() {
    fun getPopularMovies() = MovieRepository().getPopularMovies()

}