package com.example.moviedb

import com.example.moviedb.model.MovieDetail
import com.example.moviedb.ui.movieDetail.MovieDetailRepository
import io.reactivex.observers.TestObserver
import org.junit.jupiter.api.Test


 class MovieDetailRepositoryTest {

    var movieDetailRepository  = MovieDetailRepository()

    @Test
    fun getMovieDetail() {
        val results = movieDetailRepository.getMovieDetail(597993)
        val testObserver : TestObserver<MovieDetail> =  results!!.test()
        testObserver.assertSubscribed()
        testObserver.assertNoErrors()
        testObserver.assertNoTimeout()
    }
}