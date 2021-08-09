package com.example.moviedb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviedb.R
import com.example.moviedb.ui.movieList.MovieListActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        viewMovies.setOnClickListener {
            Intent(this, MovieListActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}