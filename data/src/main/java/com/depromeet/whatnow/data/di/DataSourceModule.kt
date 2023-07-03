package com.depromeet.whatnow.data.di

import com.depromeet.whatnow.data.source.PromisesRemoteDataSource
import com.depromeet.whatnow.data.source.PromisesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindPromisesDataSource(source : PromisesRemoteDataSourceImpl) : PromisesRemoteDataSource
}