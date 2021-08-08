package com.example.moviedb.model

import java.util.ArrayList

class MovieResponse {
    private val results  =  ArrayList<Movie>()

    fun getResults(): ArrayList<Movie> {
        return results
    }
}