package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.model.response.PromisesImagesResponse
import com.depromeet.whatnow.data.model.response.UsersProfileResponse

interface UsersRemoteDataSource {
    suspend fun postUsersMeImageSuccess(imageKey: String): Unit
    suspend fun getUsersMeImages(fileExtension: String): Result<PromisesImagesResponse>
    suspend fun getUsersMe(): Result<UsersProfileResponse>
}