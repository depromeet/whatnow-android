package com.depromeet.whatnow.data.model.request

import com.depromeet.whatnow.domain.model.Register
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("profileImage") val profileImage: String,
    @SerializedName("isDefaultImage") val isDefaultImage: Boolean,
    @SerializedName("nickName") val nickName: String,
    @SerializedName("fcmToken") val fcmToken: String,
    @SerializedName("appAlarm") val appAlarm: Boolean,
)

fun Register.toData() = RegisterRequest(
    profileImage = profileImage,
    isDefaultImage = isDefaultImage,
    nickName = nickName,
    fcmToken = fcmToken,
    appAlarm = appAlarm
)