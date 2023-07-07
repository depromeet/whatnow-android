package com.depromeet.whatnow.data.model.request

import com.depromeet.whatnow.domain.model.Register
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("profileImage") val profileImage: String,
    @SerializedName("isDefaultImage") val isDefaultImage: Boolean,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("fcmToken") val fcmToken: String,
    @SerializedName("appAlarm") val appAlarm: Boolean,
)

fun Register.toData() = RegisterRequest(
    profileImage = profileImage,
    isDefaultImage = isDefaultImage,
    nickname = nickname,
    fcmToken = fcmToken,
    appAlarm = appAlarm
)