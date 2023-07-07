package com.depromeet.whatnow.data.entity

import com.depromeet.whatnow.domain.model.JwtToken

data class JwtTokenEntity(
    val accessToken: String,
    val refreshToken: String,
)

internal fun JwtTokenEntity.toDomain() = JwtToken(
    accessToken = accessToken,
    refreshToken = refreshToken
)