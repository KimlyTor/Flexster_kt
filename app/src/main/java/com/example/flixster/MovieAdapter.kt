package com.example.flixster

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val TAG = "MovieAdapter"
//Take in two parameters: context and movies list
class MovieAdapter(private val context: Context, private val movies: List<Movie>):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    //return ViewHolder() of each item
    //Expensive operation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    //Cheap operation by recycling the view holder (show only a 3 movies at a time)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindviewHolder position $position")
        val movie = movies[position]
        holder.bind(movie)


    }

    //return the list size
    override fun getItemCount(): Int {
        return movies.size
    }

    //RecyclerView initiated
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverView = itemView.findViewById<TextView>(R.id.tvOverview)
        private val  ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverView.text = movie.overview

            //for 8. add poster images using Glides library
            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
        }
    }
}
 //tv = text view
//iv = image view