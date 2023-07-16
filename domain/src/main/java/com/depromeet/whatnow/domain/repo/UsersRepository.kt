package com.depromeet.whatnow.domain.repo

import com.depromeet.whatnow.domain.model.GetImagePresignedUrl
import com.depromeet.whatnow.domain.model.PostImagesUsersMe
import com.depromeet.whatnow.domain.model.PromisesImages
import com.depromeet.whatnow.domain.model.UsersProfile
import java.io.File

interface UsersRepository {
    suspend fun postUsersMeImageSuccess(imageKey: String): Unit
    suspend fun getUsersMeImages(fileExtension: String): Result<PromisesImages>
    suspend fun getUsersMe(): Result<UsersProfile>

    suspend fun logout(): Result<Unit>

    suspend fun withdraw(): Result<Unit>

    suspend fun getImagePresignedUrl(extension: String): Result<GetImagePresignedUrl>

    suspend fun postImageUsersMe(
        imageKey: String,
        extension: String
    ): Result<PostImagesUsersMe>

    suspend fun postImagePresignedUrl(url: String, file: File): Result<Unit>
}
