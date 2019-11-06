package com.example.testeappmoove.ui.MovieSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.testeappmoove.data.entities.MovieResponse
import com.example.testeappmoove.data.repositories.MovieRepository

class MovieSearchActivityViewModel : ViewModel() {

    fun searchMovies(query: String) = MovieRepository().searchMovies(query)
}
