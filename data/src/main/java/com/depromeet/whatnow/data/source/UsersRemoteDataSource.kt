package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.model.response.PromisesImagesResponse

interface UsersRemoteDataSource {
    suspend fun postUsersMeImageSuccess(imageKey: String): Unit
    suspend fun getUsersMeImages(fileExtension: String): Result<PromisesImagesResponse>
}