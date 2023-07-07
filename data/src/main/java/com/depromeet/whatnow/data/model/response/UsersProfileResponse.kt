package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.UsersProfile
import com.google.gson.annotations.SerializedName

data class UsersProfileResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("profileImg") val profileImg: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("isDefaultImg") val isDefaultImg: Boolean,
    @SerializedName("oauthProvider") val oauthProvider: OauthProvider,
    @SerializedName("fcmInfo") val fcmInfo: FcmInfoResponse,
) {
    enum class OauthProvider {
        KAKAO
    }
}

internal fun UsersProfileResponse.OauthProvider.toData() =
    when (this) {
        UsersProfileResponse.OauthProvider.KAKAO -> UsersProfile.OauthProvider.KAKAO
    }


internal fun UsersProfileResponse.toData() = UsersProfile(
    id = id,
    profileImg = profileImg,
    nickname = nickname,
    isDefaultImg = isDefaultImg,
    oauthProvider = oauthProvider.toData(),
    fcmInfo = fcmInfo.toData()
)