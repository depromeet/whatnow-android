package com.depromeet.whatnow.data.model.request

import com.depromeet.whatnow.domain.model.UsersFcmToken
import com.google.gson.annotations.SerializedName

data class UsersFcmTokenRequest(
    @SerializedName("fcmToken") val fcmToken: String,
)

internal fun UsersFcmToken.toData() = UsersFcmTokenRequest(
    fcmToken = fcmToken
)