package com.example.moviedb.base

import com.example.moviedb.di.AppModule
import com.example.moviedb.di.DaggerRepositoryInjector
import com.example.moviedb.di.RepositoryInjector
import com.example.moviedb.di.RepositoryModule
import com.example.moviedb.ui.movieDetail.MovieDetailRepository
import com.example.moviedb.ui.movieList.MovieListRepository

abstract class BaseRepository {

    private val injector: RepositoryInjector = DaggerRepositoryInjector
        .builder()
        .appModule(AppModule)
        .repositoryModule(RepositoryModule)
        .build()

    init {
        inject()
    }
    private fun inject(){
        when(this){
            is MovieListRepository ->injector.inject(this)
            is MovieDetailRepository -> injector.inject(this)
        }
    }
}