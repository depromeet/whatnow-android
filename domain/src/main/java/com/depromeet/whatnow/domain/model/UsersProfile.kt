package com.depromeet.whatnow.domain.model

data class UsersProfile(
    val id: Int,
    val profileImg: String,
    val nickname: String,
    val isDefaultImg: Boolean,
    val oauthProvider: String,
    val fcmInfo: FcmInfo,
)