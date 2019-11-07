package com.example.testeappmoove.ui.PopularMovies

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val movieRepository = MovieRepository.getInstance(likeDao!!)

        val linearLayoutManager = LinearLayoutManager(this@PopularMoviesActivity)

        movieRepository?.let {
            val viewModel =
                ViewModelProviders.of(this, PopularMoviesViewModelFactory(movieRepository))
                    .get(PopularMoviesViewModel::class.java)

            val onClick = { movie: Movie ->
                val intent = Intent(this, MovieDetailsActivity::class.java)
                intent.putExtra("movieId", movie.id)
                startActivity(intent)
            }

            val onLike = { movie: Movie ->
                viewModel.likeMovie(movie.id)
            }

            swipeRefresh.setOnRefreshListener {
                viewModel.loadPopularMovies()
            }

            var loading = true
            var page = 1


            // https://stackoverflow.com/questions/26543131/how-to-implement-endless-list-with-recyclerview
            // Código adaptado de Java para Kotlin

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val visibleItemCount = linearLayoutManager.childCount
                        val totalItemCount = linearLayoutManager.itemCount
                        val pastVisiblesItems =
                            linearLayoutManager.findFirstCompletelyVisibleItemPosition()

                        if (loading) {
                            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                progressBarPaging.isGone = false
                                page++
                                loading = false
                                viewModel.loadMoreMovies(page)
                            }
                        }
                    }
                }
            })


            viewModel.popularMovies.observe(this, Observer { movies ->
                val recyclerViewState = recyclerView.getLayoutManager()?.onSaveInstanceState();
                recyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = linearLayoutManager
                    adapter =
                        MoviesAdapter(
                            movies.results,
                            clickListener = { movie -> onClick(movie) },
                            likeListener = { movie -> onLike(movie) })
                }
                recyclerView.getLayoutManager()?.onRestoreInstanceState(recyclerViewState);

                progressBarPopularMovies.isGone = true
                loading = true
                progressBarPaging.isGone = true
                swipeRefresh.isRefreshing = false
            })
        }
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
