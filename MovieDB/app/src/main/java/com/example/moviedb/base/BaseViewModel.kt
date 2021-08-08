package com.example.moviedb.base

import androidx.lifecycle.ViewModel

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