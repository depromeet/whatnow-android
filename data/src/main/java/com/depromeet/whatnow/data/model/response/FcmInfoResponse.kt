package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class FcmInfoResponse(
    @SerializedName("fcmToken") val fcmToken: String,
    @SerializedName("appAlarm") val appAlarm: Boolean
)
