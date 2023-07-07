package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.api.ApiService
import com.depromeet.whatnow.data.model.request.RegisterRequest
import com.depromeet.whatnow.data.model.response.TokenAndUserResponse
import javax.inject.Inject

internal class AuthRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : AuthRemoteDataSource {
    override suspend fun postAuthOauthKakaoLogin(id_token: String): Result<TokenAndUserResponse> =
        runCatching {
            val response =
                apiService.postAuthOauthKakaoLogin(id_token = id_token)
            response.data
        }.onFailure { }

    override suspend fun postAuthKakaoRegister(
        id_token: String,
        request: RegisterRequest,
    ): Result<TokenAndUserResponse> =
        runCatching {
            val response =
                apiService.postAuthKakaoRegister(id_token = id_token, request = request)
            response.data
        }.onFailure { }
}