package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.entity.JwtTokenEntity

interface AuthLocalDataSource {
    suspend fun getJwtToken(): Result<JwtTokenEntity?>

    suspend fun saveJwtToken(jwtToken: JwtTokenEntity): Result<Unit>
}