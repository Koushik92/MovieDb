package com.example.moviedb.base

import androidx.lifecycle.ViewModel
import com.example.moviedb.di.AppModule
import com.example.moviedb.di.DaggerViewModelInjector
import com.example.moviedb.di.RepositoryModule
import com.example.moviedb.di.ViewModelInjector
import com.example.moviedb.ui.movieDetail.MovieDetailViewModel
import com.example.moviedb.ui.movieList.MovieListViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .appModule(AppModule)
        .repositoryModule(RepositoryModule)
        .build()

    init {
        inject()
    }

    private fun inject(){
        when (this) {
            is MovieListViewModel -> injector.inject(this)
            is MovieDetailViewModel -> injector.inject(this)

        }
    }
}