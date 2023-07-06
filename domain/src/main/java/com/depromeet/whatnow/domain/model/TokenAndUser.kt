package com.depromeet.whatnow.domain.model

data class TokenAndUser(
    val accessToken: String,
    val refreshToken: String,
    val user: UsersProfile,
    val accessTokenExpireIn: Int,
    val refreshTokenExpireIn: Int,
)

internal fun TokenAndUser.toData() = JwtToken(
    accessToken = accessToken,
    refreshToken = refreshToken
)