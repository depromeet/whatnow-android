package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.model.request.RegisterRequest
import com.depromeet.whatnow.data.model.response.TokenAndUserResponse

interface AuthRemoteDataSource {
    suspend fun postAuthOauthKakaoLogin(id_token: String): Result<TokenAndUserResponse>

    suspend fun postAuthKakaoRegister(
        id_token: String,
        request: RegisterRequest,
    ): Result<TokenAndUserResponse>
}