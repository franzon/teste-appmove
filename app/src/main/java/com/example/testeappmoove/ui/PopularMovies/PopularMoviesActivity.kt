package com.example.testeappmoove.ui.PopularMovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeappmoove.R
import com.example.testeappmoove.data.entities.Movie
import com.example.testeappmoove.data.entities.MovieResponse
import com.example.testeappmoove.data.repositories.MovieRepository
import com.example.testeappmoove.ui.MovieDetails.MovieDetailsActivity
import com.example.testeappmoove.ui.SearchActivity
import kotlinx.android.synthetic.main.activity_popular_movies.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movies)

        val onClick = { movie: Movie ->
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movie.id)
            startActivity(intent)
        }

        val movieDetailsViewModel =
            ViewModelProviders.of(this)
                .get(PopularMoviesViewModel::class.java)

        movieDetailsViewModel.getPopularMovies().observe(this, Observer { movies ->
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@PopularMoviesActivity)
                adapter =
                    MoviesAdapter(movies.results) { movie -> onClick(movie) }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
