package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.FcmInfo
import com.google.gson.annotations.SerializedName

data class FcmInfoResponse(
    @SerializedName("fcmToken") val fcmToken: String,
    @SerializedName("appAlarm") val appAlarm: Boolean,
)

internal fun FcmInfoResponse.toDomain() = FcmInfo(
    fcmToken = fcmToken,
    appAlarm = appAlarm
)