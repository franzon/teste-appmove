package com.example.testeappmoove.ui.PopularMovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeappmoove.R
import com.example.testeappmoove.data.entities.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(
    private val movies: List<Movie>,
    val clickListener: (Movie) -> Unit,
    val likeListener: (Movie) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun getItemCount() = movies.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        // Se for o primeiro item, renderiza um layout diferente
        if (viewType == 0) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.first_movie_item, parent, false)
            return MovieViewHolder(view)
        }
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title?.text = movie.title
        holder.overview?.text = movie.overview

        var imageView = holder.imageView

        if (imageView != null) {
            if (position == 0) {
                Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.backdrop_path)
                    .into(imageView)
            } else {
                Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.poster_path)
                    .resize(250, 250)
                    .into(imageView)
            }
        }

        if (movie.liked) {
            holder.likeButton.setBackgroundResource(R.drawable.ic_favorite_filled)
        }

        holder.likeButton.setOnClickListener {
            movie.liked = !movie.liked
            notifyItemChanged(position)
            likeListener(movies[position])
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.title
        var imageView = itemView.imageView
        var overview = itemView.overview
        var likeButton = itemView.likeButton

        init {
            itemView.setOnClickListener {
                clickListener(movies[adapterPosition])
            }
        }
    }
}

