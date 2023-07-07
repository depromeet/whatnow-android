package com.depromeet.whatnow.domain.repo

import com.depromeet.whatnow.domain.model.Register
import com.depromeet.whatnow.domain.model.TokenAndUser

interface AuthRepository {
    suspend fun postAuthOauthKakaoLogin(id_token: String): Result<TokenAndUser>

    suspend fun postAuthKakaoRegister(
        id_token: String,
        request: Register,
    ): Result<TokenAndUser>
}