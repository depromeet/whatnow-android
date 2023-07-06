package com.depromeet.whatnow.data.repo

import com.depromeet.whatnow.data.source.UsersRemoteDataSource
import com.depromeet.whatnow.domain.repo.UsersRepository
import javax.inject.Inject

internal class UsersRepositoryImpl @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource
) : UsersRepository {

    override suspend fun postUsersMeImageSuccess(imageKey: String) =
        usersRemoteDataSource.postUsersMeImageSuccess(imageKey = imageKey)
}