package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.OauthUserInfo
import com.google.gson.annotations.SerializedName

data class OauthUserInfoResponse(
    @SerializedName("profileImage") val profileImage: String,
    @SerializedName("isDefaultImage") val isDefaultImage: Boolean,
    @SerializedName("nickname") val nickname: String,
)

internal fun OauthUserInfoResponse.toDomain() = OauthUserInfo(
    profileImage = profileImage,
    isDefaultImage = isDefaultImage,
    nickname = nickname
)