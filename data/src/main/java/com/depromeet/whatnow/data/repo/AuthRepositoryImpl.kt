package com.depromeet.whatnow.data.repo

import android.util.Log
import com.depromeet.whatnow.data.entity.toDomain
import com.depromeet.whatnow.data.model.request.toData
import com.depromeet.whatnow.data.model.response.toDomain
import com.depromeet.whatnow.data.model.response.toJwt
import com.depromeet.whatnow.data.source.AuthLocalDataSource
import com.depromeet.whatnow.data.source.AuthRemoteDataSource
import com.depromeet.whatnow.domain.model.*
import com.depromeet.whatnow.domain.repo.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {
    override suspend fun getJwtToken(): Result<JwtToken> = authLocalDataSource
        .getJwtToken()
        .map { it?.toDomain() ?: JwtToken("", "") }

    override suspend fun getIdToken(): Result<String?> = authLocalDataSource
        .getIdToken()

    override suspend fun dataReset(): Result<Unit> = authLocalDataSource
        .reset()


    override suspend fun postAuthOauthKakaoLogin(
        id_token: String,
        usersFcmToken: UsersFcmToken,
    ): Result<TokenAndUser> =
        authRemoteDataSource.postAuthOauthKakaoLogin(id_token = id_token,
            usersFcmTokenRequest = usersFcmToken.toData())
            .onSuccess {
                Log.d("yw", "로그인 엑세스토큰 it.toJwt() = ${it.toJwt()}")
                Log.d("yw", "로그인 하기위한 id_token = $id_token")

                authLocalDataSource.saveIdToken(id_token = id_token)
                authLocalDataSource.saveJwtToken(it.toJwt())
            }
            .map { it.toDomain() }

    override suspend fun postAuthKakaoRegister(
        id_token: String,
        request: Register,
    ): Result<TokenAndUser> =
        authRemoteDataSource.postAuthKakaoRegister(id_token = id_token, request = request.toData())
            .map { it.toDomain() }

    override suspend fun postAuthKakaoInfo(access_token: String): Result<OauthUserInfo> =
        authRemoteDataSource.postAuthKakaoInfo(access_token = access_token).map { it.toDomain() }

    override suspend fun getAuthOauthKakaoRegisterValid(id_token: String): Result<AbleRegister> =
        authRemoteDataSource.getAuthOauthKakaoRegisterValid(id_token = id_token)
            .map { it.toDomain() }

    override suspend fun deleteAutoMe() {
        authRemoteDataSource.deleteAutoMe()
    }

}