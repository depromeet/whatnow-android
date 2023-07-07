package com.depromeet.whatnow.domain.model

data class UsersProfile(
    val id: Int,
    val profileImg: String,
    val nickname: String,
    val isDefaultImg: Boolean,
    val oauthProvider: OauthProvider,
    val fcmInfo: FcmInfo,
) {
    enum class OauthProvider { KAKAO }
}
