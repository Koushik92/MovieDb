package com.example.moviedb.ui.movieList

import com.example.moviedb.model.Movie
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MovieListViewModelTest {

    private lateinit var mMovieListViewModel : MovieListViewModel

    @BeforeEach
    fun setUp() {
        mMovieListViewModel = MovieListViewModel()
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun testMovieListByName(){
        assertEquals(getSortedMovieListNames(),mMovieListViewModel.sortMovieByName(getRandomMovieListNames()))
    }

    private fun getRandomMovieListNames(): ArrayList<Movie> {
        var randomMovieListNames = ArrayList<Movie>()
        randomMovieListNames.add(Movie(id =1,name = "A"))
        randomMovieListNames.add(Movie(id =2,name = "Z"))
        randomMovieListNames.add(Movie(id =3,name = "E"))
        return randomMovieListNames
    }

    private fun getSortedMovieListNames(): ArrayList<Movie> {
        var randomMovieListNames = ArrayList<Movie>()
        randomMovieListNames.add(Movie(id =1,name = "A"))
        randomMovieListNames.add(Movie(id =3,name = "E"))
        randomMovieListNames.add(Movie(id =2,name = "Z"))
        return randomMovieListNames
    }}