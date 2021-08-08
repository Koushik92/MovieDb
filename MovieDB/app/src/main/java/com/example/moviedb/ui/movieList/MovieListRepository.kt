package com.example.moviedb.ui.movieList

import com.example.moviedb.base.BaseRepository
import com.example.moviedb.model.Movie
import com.example.moviedb.model.MovieResponse
import com.example.moviedb.network.ApiMethods
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class MovieListRepository : BaseRepository(){

    @set: Inject
    var mApiMethods : ApiMethods? = null

    fun getTopMovies(mPageCount: Int): Observable<ArrayList<Movie>>? {

        mApiMethods?.let{ apiMethods ->
            return  apiMethods.getTopRelated(mPageCount)
                .map(MovieResponse::getResults)
                .subscribeOn(Schedulers.io())

        }?: run {
            return null
        }
    }

}