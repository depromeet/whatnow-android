package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.model.response.GetImagePresignedUrlResponse
import com.depromeet.whatnow.data.model.response.PostImagesUsersMeResponse
import com.depromeet.whatnow.data.model.response.PromisesImagesResponse
import com.depromeet.whatnow.data.model.response.UsersProfileResponse
import java.io.File

interface UsersRemoteDataSource {
    suspend fun postUsersMeImageSuccess(imageKey: String): Unit
    suspend fun getUsersMeImages(fileExtension: String): Result<PromisesImagesResponse>
    suspend fun getUsersMe(): Result<UsersProfileResponse>

    suspend fun logout(): Result<Unit>

    suspend fun withdraw(): Result<Unit>

    suspend fun getImagePresignedUrl(extension: String): Result<GetImagePresignedUrlResponse>

    suspend fun postImageUsersMe(
        imageKey: String,
        extension: String
    ): Result<PostImagesUsersMeResponse>

    suspend fun postImagePresignedUrl(url: String, file: File): Result<Unit>

    suspend fun patchUsersProfile(
        nickname: String,
        profileImageUrl: String,
        imageKey: String,
        isDefaultImage: Boolean
    ): Result<UsersProfileResponse>
}
