package com.depromeet.whatnow.domain.model

data class JwtToken(
    val accessToken: String,
    val refreshToken: String,
)
