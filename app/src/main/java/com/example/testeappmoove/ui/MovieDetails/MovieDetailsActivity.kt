package com.example.testeappmoove.ui.MovieDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testeappmoove.R
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent.getIntExtra("movieId", -1)

        val movieDetailsViewModel =
            ViewModelProviders.of(this, MovieDetailsViewModelFactory(movieId))
                .get(MovieDetailsViewModel::class.java)

        movieDetailsViewModel.getMovie().observe(this, Observer { movie ->
            movie_title.text = movie.title
        })
    }
}
