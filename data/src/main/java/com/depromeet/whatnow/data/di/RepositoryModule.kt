package com.depromeet.whatnow.data.di

import com.depromeet.whatnow.data.repo.AuthRepositoryImpl
import com.depromeet.whatnow.data.repo.PromisesRepositoryImpl
import com.depromeet.whatnow.data.repo.UsersRepositoryImpl
import com.depromeet.whatnow.domain.repo.AuthRepository
import com.depromeet.whatnow.domain.repo.PromisesRepository
import com.depromeet.whatnow.domain.repo.UsersRepository
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

    @Binds
    @Singleton
    abstract fun bindUsersRepository(repo: UsersRepositoryImpl): UsersRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(repo: AuthRepositoryImpl): AuthRepository

}