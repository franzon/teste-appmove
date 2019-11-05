package com.example.testeappmoove.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testeappmoove.R
import com.example.testeappmoove.data.Movie
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
    }
}