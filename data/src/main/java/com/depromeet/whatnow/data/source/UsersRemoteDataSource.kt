package com.depromeet.whatnow.data.source

interface UsersRemoteDataSource {
    suspend fun postUsersMeImageSuccess(imageKey: String): Unit
}