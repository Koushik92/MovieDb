package com.example.moviedb.network

import com.example.moviedb.BuildConfig
import com.example.moviedb.model.MovieDetail
import com.example.moviedb.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMethods{


    @GET("tv/top_rated?"+BuildConfig.BASE_API_KEY)
    fun getTopRelated(@Query("page")page : Int) : Observable<MovieResponse>


    @GET("movie/{id}?"+BuildConfig.BASE_API_KEY2)
    fun getMovieDetail(@Path("id")  movieId :Long) : Observable<MovieDetail>

}