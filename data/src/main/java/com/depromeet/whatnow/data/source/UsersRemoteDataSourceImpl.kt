package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.api.ApiService
import com.depromeet.whatnow.data.model.response.GetImagePresignedUrlResponse
import com.depromeet.whatnow.data.model.response.PostImagesUsersMeResponse
import com.depromeet.whatnow.data.model.response.PromisesImagesResponse
import com.depromeet.whatnow.data.model.response.UsersProfileResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

internal class UsersRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : UsersRemoteDataSource {
    override suspend fun postUsersMeImageSuccess(imageKey: String) {
        runCatching {
            apiService.postUsersMeImageSuccess(imageKey = imageKey)
        }
    }

    override suspend fun getUsersMeImages(fileExtension: String): Result<PromisesImagesResponse> =
        runCatching {
            apiService.getUsersMeImages(fileExtension = fileExtension).data
        }

    override suspend fun getUsersMe(): Result<UsersProfileResponse> =
        runCatching {
            apiService.getUsersMe().data
        }

    override suspend fun logout(): Result<Unit> = runCatching {
        apiService.postAuthLogout()
    }

    override suspend fun withdraw(): Result<Unit> = runCatching {
        apiService.deleteAuthMe()
    }

    override suspend fun getImagePresignedUrl(extension: String): Result<GetImagePresignedUrlResponse> =
        runCatching {
            apiService.getImagesUsersMePresignedUrl(extension)
        }

    override suspend fun postImagePresignedUrl(url: String, file: File): Result<Unit> =
        runCatching {
            apiService.uploadImage(
                url = url,
                body = file.asRequestBody("application/octet-stream".toMediaTypeOrNull())
            )
        }

    override suspend fun postImageUsersMe(
        imageKey: String,
        extension: String
    ): Result<PostImagesUsersMeResponse> = runCatching {
        apiService.postImagesUsersMe(imageKey, extension)
    }
}