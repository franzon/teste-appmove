package com.example.testeappmoove.ui.MovieDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testeappmoove.R
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent.getIntExtra("movieId", -1)

        val movieDetailsViewModel =
            ViewModelProviders.of(this, MovieDetailsViewModelFactory(movieId))
                .get(MovieDetailsViewModel::class.java)

        movieDetailsViewModel.getMovie().observe(this, Observer { movie ->

            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.backdrop_path)
                .into(imageView)

            movie_title.text = movie.title
            movie_vote_average.text = movie.vote_average.toString()
            movie_overview.text = movie.overview

            val locale = Locale(movie.original_language)
            movie_original_language.text = locale.getDisplayLanguage()

            val date = SimpleDateFormat("yyyy-MM-dd").parse(movie.release_date)
            val newDate = SimpleDateFormat("dd/MM/yyyy").format(date)

            movie_release_date.text = newDate

            for (genre in movie.genres) {
                val chip = Chip(chipGroup.context)

                chip.text = genre.name
                chip.isClickable = false

                chipGroup.addView(chip)
            }
        })
    }
}
