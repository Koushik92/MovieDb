package com.example.moviedb.di

import com.example.moviedb.ui.movieDetail.MovieDetailRepository
import com.example.moviedb.ui.movieList.MovieListRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), RepositoryModule::class])
interface RepositoryInjector {

    fun inject(movieListRepository: MovieListRepository)
    fun inject(movieDetailRepository: MovieDetailRepository)

    @Component.Builder
    interface Builder {
        fun build(): RepositoryInjector
        fun appModule(appModule: AppModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}