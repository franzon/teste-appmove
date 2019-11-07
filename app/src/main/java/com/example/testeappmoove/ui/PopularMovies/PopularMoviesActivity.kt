package com.example.testeappmoove.ui.PopularMovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeappmoove.R
import com.example.testeappmoove.data.database.AppDatabase
import com.example.testeappmoove.data.entities.Movie
import com.example.testeappmoove.data.repositories.MovieRepository
import com.example.testeappmoove.ui.MovieDetails.MovieDetailsActivity
import com.example.testeappmoove.ui.MovieSearch.MovieSearchActivity
import kotlinx.android.synthetic.main.activity_popular_movies.*

class PopularMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movies)


        val database = AppDatabase.getInstance(this)
        val likeDao = database?.likeDao()
        val movieRepository = MovieRepository(likeDao!!)

        val popularMoviesViewModel =
            ViewModelProviders.of(this, PopularMoviesViewModelFactory(movieRepository))
                .get(PopularMoviesViewModel::class.java)


        val onClick = { movie: Movie ->
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movie.id)
            startActivity(intent)
        }

        val onLike = { movie: Movie ->
            popularMoviesViewModel.likeMovie(movie.id)
        }



        popularMoviesViewModel.getPopularMovies().observe(this, Observer { movies ->
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@PopularMoviesActivity)
                adapter =
                    MoviesAdapter(
                        movies.results,
                        clickListener = { movie -> onClick(movie) },
                        likeListener = { movie -> onLike(movie) })
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
                val intent = Intent(this, MovieSearchActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
