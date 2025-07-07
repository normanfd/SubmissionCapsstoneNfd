package com.norman.core.di

import com.norman.core.data.MovieRepository
import com.norman.core.domain.repository.IMovieRepository
import com.norman.core.domain.usecase.MovieInteractor
import com.norman.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {
    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository

    @Binds
    abstract fun provideUseCase(movieInteractor: MovieInteractor): MovieUseCase
}