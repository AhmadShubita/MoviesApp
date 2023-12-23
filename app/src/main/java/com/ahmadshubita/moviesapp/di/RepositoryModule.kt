package com.ahmadshubita.moviesapp.di

import com.ahmadshubita.moviesapp.data.remote.repo.MainRepository
import com.ahmadshubita.moviesapp.data.remote.repo.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMainRepository(
        getMainRepositoryImpl: MainRepositoryImpl
    ): MainRepository
}