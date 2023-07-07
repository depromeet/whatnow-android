package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class InteractionsDetailResponse(
    @SerializedName("senderUser") val senderUser: UsersResponse,
    @SerializedName("count") val count: Int,
    @SerializedName("interactionType") val interactionType: String
)