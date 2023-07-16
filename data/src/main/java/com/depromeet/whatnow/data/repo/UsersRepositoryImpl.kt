package com.depromeet.whatnow.data.repo

import com.depromeet.whatnow.data.model.response.toDomain
import com.depromeet.whatnow.data.model.toDomain
import com.depromeet.whatnow.data.source.UsersRemoteDataSource
import com.depromeet.whatnow.domain.model.GetImagePresignedUrl
import com.depromeet.whatnow.domain.model.PostImagesUsersMe
import com.depromeet.whatnow.domain.model.PromisesImages
import com.depromeet.whatnow.domain.model.UsersProfile
import com.depromeet.whatnow.domain.repo.UsersRepository
import java.io.File
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

    override suspend fun logout(): Result<Unit> = usersRemoteDataSource.logout()

    override suspend fun withdraw(): Result<Unit> = usersRemoteDataSource.withdraw()

    override suspend fun getImagePresignedUrl(extension: String): Result<GetImagePresignedUrl> {
        return usersRemoteDataSource.getImagePresignedUrl(extension).mapCatching { it.toDomain() }
    }

    override suspend fun postImagePresignedUrl(url: String, file: File): Result<Unit> {
        return usersRemoteDataSource.postImagePresignedUrl(url, file)
    }

    override suspend fun postImageUsersMe(
        imageKey: String,
        extension: String
    ): Result<PostImagesUsersMe> {
        return usersRemoteDataSource.postImageUsersMe(imageKey, extension)
            .mapCatching { it.toDomain() }
    }

    override suspend fun patchUsersProfile(
        username: String,
        profileImageUrl: String,
        imageKey: String,
        isDefaultImage: Boolean
    ): Result<UsersProfile> {
        return usersRemoteDataSource.patchUsersProfile(
            username,
            profileImageUrl,
            imageKey,
            isDefaultImage
        ).mapCatching { it.toDomain() }
    }
}
