package com.example.flixster

import org.json.JSONArray

//parse the API json data from MainActivity to this data class
//by calling fromJsonArray() inside MainActivity
//represent one movie object display to the UI
//only pick the attributes you want from the API results
data class Movie (
    val movieId: Int,
    private val posterPath: String,
    val title: String,
    val overview: String,
){
    //absolute path to the image with the size of 342px
    //check this for more sizes https://api.themoviedb.org/3/configuration?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed
    val posterImageUrl = "https://image.tmdb.org/t/p/w342/$posterPath"
    companion object{
        //take in the result of API call from MainActivity
        //return a list of Movie objects (Movie data type)
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()){
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview")

                    )
                )

            }
            return movies
        }
    }
}