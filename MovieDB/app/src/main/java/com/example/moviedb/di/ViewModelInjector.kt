package com.example.moviedb.di

import com.example.moviedb.ui.movieDetail.MovieDetailViewModel
import com.example.moviedb.ui.movieList.MovieListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), RepositoryModule::class])
interface ViewModelInjector {

    fun inject(movieListViewModel: MovieListViewModel)
    fun inject(movieDetailViewModel: MovieDetailViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun appModule(appModule: AppModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}