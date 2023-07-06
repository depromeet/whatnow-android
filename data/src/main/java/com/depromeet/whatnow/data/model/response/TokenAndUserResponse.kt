package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.data.entity.JwtTokenEntity
import com.depromeet.whatnow.domain.model.TokenAndUser
import com.google.gson.annotations.SerializedName

data class TokenAndUserResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("user") val user: UsersProfileResponse,
    @SerializedName("accessTokenExpireIn") val accessTokenExpireIn: Int,
    @SerializedName("refreshTokenExpireIn") val refreshTokenExpireIn: Int,
)

internal fun TokenAndUserResponse.toDomain() = TokenAndUser(
    accessToken = accessToken,
    refreshToken = refreshToken,
    user = user.toData(),
    accessTokenExpireIn = accessTokenExpireIn,
    refreshTokenExpireIn = refreshTokenExpireIn
)

internal fun TokenAndUserResponse.toJwt() = JwtTokenEntity(
    accessToken = accessToken,
    refreshToken = refreshToken
)