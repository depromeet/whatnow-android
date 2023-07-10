package com.depromeet.whatnow.domain.repo

import com.depromeet.whatnow.domain.model.PromisesImages
import com.depromeet.whatnow.domain.model.UsersProfile

interface UsersRepository {
    suspend fun postUsersMeImageSuccess(imageKey: String): Unit
    suspend fun getUsersMeImages(fileExtension: String): Result<PromisesImages>
    suspend fun getUsersMe(): Result<UsersProfile>
}