package com.depromeet.whatnow.domain.repo

interface UsersRepository {
    suspend fun postUsersMeImageSuccess(imageKey: String): Unit

}