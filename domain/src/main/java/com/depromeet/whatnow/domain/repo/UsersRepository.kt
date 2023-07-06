package com.depromeet.whatnow.domain.repo

import com.depromeet.whatnow.domain.model.PromisesImages

interface UsersRepository {
    suspend fun postUsersMeImageSuccess(imageKey: String): Unit
    suspend fun getUsersMeImages(fileExtension: String): Result<PromisesImages>

}