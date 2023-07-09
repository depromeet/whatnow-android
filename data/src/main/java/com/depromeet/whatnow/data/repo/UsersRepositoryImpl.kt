package com.depromeet.whatnow.data.repo

import com.depromeet.whatnow.data.model.response.toDomain
import com.depromeet.whatnow.data.model.toDomain
import com.depromeet.whatnow.data.source.UsersRemoteDataSource
import com.depromeet.whatnow.domain.model.PromisesImages
import com.depromeet.whatnow.domain.model.UsersProfile
import com.depromeet.whatnow.domain.repo.UsersRepository
import javax.inject.Inject

internal class UsersRepositoryImpl @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource
) : UsersRepository {

    override suspend fun postUsersMeImageSuccess(imageKey: String) =
        usersRemoteDataSource.postUsersMeImageSuccess(imageKey = imageKey)

    override suspend fun getUsersMeImages(fileExtension: String): Result<PromisesImages> =
        usersRemoteDataSource.getUsersMeImages(fileExtension = fileExtension).mapCatching {
            it.toDomain()
        }

    override suspend fun getUsersMe(): Result<UsersProfile> =
        usersRemoteDataSource.getUsersMe().mapCatching {
            it.toDomain()
        }
}