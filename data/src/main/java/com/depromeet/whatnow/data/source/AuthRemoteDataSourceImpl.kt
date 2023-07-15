package com.depromeet.whatnow.data.source

import android.util.Log
import com.depromeet.whatnow.data.api.ApiService
import com.depromeet.whatnow.data.model.request.RegisterRequest
import com.depromeet.whatnow.data.model.request.UsersFcmTokenRequest
import com.depromeet.whatnow.data.model.response.AbleRegisterResponse
import com.depromeet.whatnow.data.model.response.OauthUserInfoResponse
import com.depromeet.whatnow.data.model.response.TokenAndUserResponse
import javax.inject.Inject

internal class AuthRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : AuthRemoteDataSource {
    override suspend fun postAuthOauthKakaoLogin(
        id_token: String,
        usersFcmTokenRequest: UsersFcmTokenRequest,
    ): Result<TokenAndUserResponse> =
        runCatching {
            val response =
                apiService.postAuthOauthKakaoLogin(id_token = id_token, body = usersFcmTokenRequest)
            response.data
        }.onFailure { }

    override suspend fun postAuthKakaoRegister(
        id_token: String,
        request: RegisterRequest,
    ): Result<TokenAndUserResponse> =
        runCatching {
            val response =
                apiService.postAuthKakaoRegister(id_token = id_token, body = request)
            response.data
        }.onFailure { Log.d("yw", "여기서부터 실패 $it") }

    override suspend fun postAuthKakaoInfo(access_token: String): Result<OauthUserInfoResponse> =
        runCatching {
            val response = apiService.postAuthKakaoInfo(access_token = access_token)
            response.data
        }

    override suspend fun getAuthOauthKakaoRegisterValid(id_token: String): Result<AbleRegisterResponse> =
        runCatching {
            val response = apiService.getAuthOauthKakaoRegisterValid(id_token = id_token)
            response.data
        }

    override suspend fun deleteAutoMe() {
        apiService.deleteAuthMe()
    }

}