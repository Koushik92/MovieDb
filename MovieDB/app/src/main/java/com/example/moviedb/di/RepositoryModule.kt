package com.example.moviedb.di

import com.example.moviedb.ui.movieDetail.MovieDetailRepository
import com.example.moviedb.ui.movieList.MovieListRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
@Suppress("unused")
object RepositoryModule {
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideMovieListRepository(): MovieListRepository {
        return MovieListRepository()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideMovieDetailRepository(): MovieDetailRepository {
        return MovieDetailRepository()
    }


}