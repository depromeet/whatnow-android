package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.api.ApiService
import com.depromeet.whatnow.data.model.response.PromisesImagesResponse
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
}