package com.depromeet.whatnow.data.di

import com.depromeet.whatnow.data.repo.PromisesRepositoryImpl
import com.depromeet.whatnow.domain.repo.PromisesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPromisesRepository(repo: PromisesRepositoryImpl): PromisesRepository
}