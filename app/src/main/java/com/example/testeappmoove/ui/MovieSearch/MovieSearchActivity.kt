package com.example.testeappmoove.ui.MovieSearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeappmoove.R
import com.example.testeappmoove.data.database.AppDatabase
import com.example.testeappmoove.data.entities.Movie
import com.example.testeappmoove.data.repositories.MovieRepository
import com.example.testeappmoove.ui.MovieDetails.MovieDetailsActivity
import kotlinx.android.synthetic.main.activity_search.*

class MovieSearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val database = AppDatabase.getInstance(this)
        val likeDao = database?.likeDao()
        val movieRepository = MovieRepository(likeDao!!)

        val movieSearchViewModel =
            ViewModelProviders.of(this, MovieSearchActivityViewModelFactory( movieRepository))
                .get(MovieSearchActivityViewModel::class.java)

        val onClick = { movie: Movie ->
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movie.id)
            startActivity(intent)
        }


        search.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                movieSearchViewModel.searchMovies(search.text.toString()).observe(this, Observer {
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MovieSearchActivity)
                        adapter =
                            MovieSearchAdapter(it.results) { movie -> onClick(movie) }
                    }
                })

                true
            } else {
                false
            }
        }


    }
}
