package com.depromeet.whatnow.data.entity

data class JwtTokenEntity(
    val accessToken: String,
    val refreshToken: String,
)