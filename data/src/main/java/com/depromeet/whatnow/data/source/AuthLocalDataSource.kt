package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.entity.JwtTokenEntity

interface AuthLocalDataSource {
    suspend fun getJwtToken(): Result<JwtTokenEntity?>

    suspend fun saveJwtToken(jwtToken: JwtTokenEntity): Result<Unit>

    suspend fun saveIdToken(id_token: String): Result<Unit>

    suspend fun getIdToken(): Result<String?>

    suspend fun reset(): Result<Unit>
}