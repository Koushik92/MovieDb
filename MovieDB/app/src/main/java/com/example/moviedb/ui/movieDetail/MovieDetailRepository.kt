package com.example.moviedb.ui.movieDetail

import com.example.moviedb.base.BaseRepository
import com.example.moviedb.model.MovieDetail
import com.example.moviedb.network.ApiMethods
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailRepository : BaseRepository() {

    @set: Inject
    var mApiMethods : ApiMethods? = null

    fun getMovieDetail(movieId : Long): Observable<MovieDetail>? {
        mApiMethods?.let {apiMethods ->
            return   apiMethods.getMovieDetail(movieId)
                .subscribeOn(Schedulers.io())
        }  ?: run {
            return null
        }
    }
}