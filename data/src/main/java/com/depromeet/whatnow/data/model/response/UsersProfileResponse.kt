package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class UsersProfileResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("profileImg") val profileImg: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("isDefaultImg") val isDefaultImg: Boolean,
    @SerializedName("oauthProvider") val oauthProvider: String,
    @SerializedName("fcmInfo") val fcmInfo: FcmInfoResponse
)