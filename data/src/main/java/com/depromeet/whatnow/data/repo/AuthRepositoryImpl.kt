package com.depromeet.whatnow.data.repo

import com.depromeet.whatnow.data.model.request.toData
import com.depromeet.whatnow.data.model.response.toDomain
import com.depromeet.whatnow.data.model.response.toJwt
import com.depromeet.whatnow.data.source.AuthLocalDataSource
import com.depromeet.whatnow.data.source.AuthRemoteDataSource
import com.depromeet.whatnow.domain.model.Register
import com.depromeet.whatnow.domain.model.TokenAndUser
import com.depromeet.whatnow.domain.repo.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {
    override suspend fun postAuthOauthKakaoLogin(id_token: String): Result<TokenAndUser> =
        authRemoteDataSource.postAuthOauthKakaoLogin(id_token = id_token)
            .onSuccess { authLocalDataSource.saveJwtToken(it.toJwt()) }
            .map { it.toDomain() }

    override suspend fun postAuthKakaoRegister(
        id_token: String,
        request: Register,
    ): Result<TokenAndUser> =
        authRemoteDataSource.postAuthKakaoRegister(id_token = id_token, request = request.toData())
            .map { it.toDomain() }
}