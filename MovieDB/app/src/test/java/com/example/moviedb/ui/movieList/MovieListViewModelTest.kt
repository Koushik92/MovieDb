package com.example.moviedb.ui.movieList

import com.example.moviedb.model.Movie
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

 class MovieListViewModelTest {

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

     @Test
     fun testMovieListByDate(){
         assertEquals(getSortedMovieListDate(),mMovieListViewModel.sortMovieByDate(getRandomMovieListByDate()))
     }

    private fun getRandomMovieListNames(): ArrayList<Movie> {
        var randomMovieListNames = ArrayList<Movie>()
        randomMovieListNames.add(Movie(id =1,name = "A"))
        randomMovieListNames.add(Movie(id =2,name = "Z"))
        randomMovieListNames.add(Movie(id =3,name = "E"))
        return randomMovieListNames
    }

   private fun getRandomMovieListByDate() : ArrayList<Movie>{
       var randomMovieListNames = ArrayList<Movie>()
       randomMovieListNames.add(Movie(id =1,release_date = "1/2/2021"))
       randomMovieListNames.add(Movie(id =2,release_date = "8/4/2021"))
       randomMovieListNames.add(Movie(id =3,release_date = "2/2/2021"))
       return randomMovieListNames
   }

    private fun getSortedMovieListNames(): ArrayList<Movie> {
        var sortedMovieListNames = ArrayList<Movie>()
        sortedMovieListNames.add(Movie(id =1,name = "A"))
        sortedMovieListNames.add(Movie(id =3,name = "E"))
        sortedMovieListNames.add(Movie(id =2,name = "Z"))
        return sortedMovieListNames
    }

     private fun getSortedMovieListDate(): ArrayList<Movie> {
         var sortedMovieListDate = ArrayList<Movie>()
         sortedMovieListDate.add(Movie(id =1,release_date = "1/2/2021"))
         sortedMovieListDate.add(Movie(id =3,release_date = "2/2/2021"))
         sortedMovieListDate.add(Movie(id =2,release_date = "8/4/2021"))
         return sortedMovieListDate
     }
 }
