package com.example.testeappmoove.ui.PopularMovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeappmoove.R
import com.example.testeappmoove.data.MovieResponse
import com.example.testeappmoove.service.ApiFactory
import com.example.testeappmoove.ui.SearchActivity
import kotlinx.android.synthetic.main.activity_popular_movies.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movies)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =
            MoviesAdapter(arrayListOf())

        val api = ApiFactory.api

        api.getPopularMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                recyclerView.apply {
                    setHasFixedSize(true)
                    adapter =
                        MoviesAdapter(response.body()!!.results)
                    layoutManager = LinearLayoutManager(this@PopularMoviesActivity)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
