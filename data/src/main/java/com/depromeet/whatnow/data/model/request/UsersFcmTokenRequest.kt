package com.depromeet.whatnow.data.model.request

import com.google.gson.annotations.SerializedName

data class UsersFcmTokenRequest(
    @SerializedName("fcmToken") val fcmToken: String,
)