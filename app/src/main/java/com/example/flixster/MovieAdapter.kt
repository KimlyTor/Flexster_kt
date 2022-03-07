package com.example.flixster

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"
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
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverView = itemView.findViewById<TextView>(R.id.tvOverview)
        private val  ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

        //register the OnClickListener
        init{
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverView.text = movie.overview

            //for 8. add poster images using Glides library
            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
        }

        override fun onClick(v: View?) {
            //1. Get notified of the particular movie which was clicked
            val movie = movies[adapterPosition]
            //Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()

            //2. Use the intent system to navigate to the new activity
            val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra("movie_title", movie.title)

            //must Parcelize first (update Gradle, and @Parcelize in Movie clasS)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }
}
 //tv = text view
//iv = image view