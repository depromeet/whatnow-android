package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.model.request.RegisterRequest
import com.depromeet.whatnow.data.model.request.UsersFcmTokenRequest
import com.depromeet.whatnow.data.model.response.AbleRegisterResponse
import com.depromeet.whatnow.data.model.response.OauthUserInfoResponse
import com.depromeet.whatnow.data.model.response.TokenAndUserResponse

interface AuthRemoteDataSource {
    suspend fun postAuthOauthKakaoLogin(
        id_token: String,
        usersFcmTokenRequest: UsersFcmTokenRequest,
    ): Result<TokenAndUserResponse>

    suspend fun postAuthKakaoRegister(
        id_token: String,
        request: RegisterRequest,
    ): Result<TokenAndUserResponse>

    suspend fun postAuthKakaoInfo(access_token: String): Result<OauthUserInfoResponse>

    suspend fun getAuthOauthKakaoRegisterValid(id_token: String): Result<AbleRegisterResponse>

    suspend fun deleteAutoMe()
}