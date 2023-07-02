package com.depromeet.whatnow.data.api

import com.depromeet.whatnow.data.model.RefreshResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RefreshApiService {

    @GET("user/refresh")
    suspend fun refresh(
        @Query("refresh_token") refreshToken: String,
    ): RefreshResponse
}
