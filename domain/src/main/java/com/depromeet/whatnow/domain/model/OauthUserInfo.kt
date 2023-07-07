package com.depromeet.whatnow.domain.model

data class OauthUserInfo(
    val email: String,
    val profileImage: String,
    val isDefaultImage: Boolean,
    val nickname: String,
)
