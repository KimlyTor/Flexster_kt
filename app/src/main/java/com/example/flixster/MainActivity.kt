package com.example.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "MainActivity"

//api key was given
private const val NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
class MainActivity : AppCompatActivity() {
    //An empty list to stores Movies object
    private val movies = mutableListOf<Movie>()

    //for 5.
    private lateinit var rvMovies: RecyclerView

    //<from data to UI section>
    //1. Define a data model class as the data source -->  Done in MainActivity and Movie
    //2. Add the RecycleView to the layout --> Done in activity_main.xml
    //3. Create a custom row layout XML file to visualize the item --> Done in item_movie.xml
    //4. Create an Adapter and ViewHolder to render the item --> Done in the MovieAdapter
    //5. Bind the adapter to the data source to populate the RecyclerView --> Done in MainActivity
    //6. Bind a layout manager to the RecyclerView --> Done in MainActivity
    // 7. updating the adapter that the data has changed --> Done in MainActivity
    // 8. add poster images using Glides library --> Done in Adapter and build.gradle (app)
    //https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library

    //</from data to UI section>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //for 5. rv = RecyclerView
        rvMovies = findViewById(R.id.rvMovies)


        //for 4. calling MovieAdapter constructor (initiate a class)
        val movieAdapter = MovieAdapter(this, movies)

        //for 5. bind rvMovies to the adapter
        rvMovies.adapter = movieAdapter

        //for 6. bind the LinearLayloutManager to rvMovies
        rvMovies.layoutManager = LinearLayoutManager(this, )

        //sending request to TheMovieDB API
        val client = AsyncHttpClient()
        client.get(NOW_PLAYING_URL, object: JsonHttpResponseHandler(){
            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.e(TAG, "onSuccess $statusCode")
                try{
                    //list of objects from API call result
                    val movieJsonArray = json.jsonObject.getJSONArray("results")

                    //Movie.fromJsonArray(movieJsonArray) returns a list of Movie objects from the Movie data class
                    // add them to the movies list
                    movies.addAll(Movie.fromJsonArray(movieJsonArray))

                    //for 7. updating the adapter that the data has changed
                    movieAdapter.notifyDataSetChanged()

                    Log.i(TAG, "Movie list $movies")

                }catch(e: JSONException){
                    Log.e(TAG, "Encounters exception $e")
                }


            }

        })

    }
}