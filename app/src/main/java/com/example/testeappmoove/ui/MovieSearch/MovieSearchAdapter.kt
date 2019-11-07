package com.example.testeappmoove.ui.MovieSearch


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeappmoove.R
import com.example.testeappmoove.data.entities.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieSearchAdapter(private val movies: List<Movie>, val clickListener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieSearchAdapter.MovieViewHolder>() {

    override fun getItemCount() = movies.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title?.text = movie.title
        holder.overview?.text = movie.overview

        var imageView = holder.imageView

        if (imageView != null && !movie.poster_path.isNullOrBlank()) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.poster_path)
                .resize(250, 250)
                .into(imageView)
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.title
        var imageView = itemView.imageView
        var overview = itemView.overview

        init {
            itemView.setOnClickListener {
                clickListener(movies[adapterPosition])
            }
        }
    }
}

