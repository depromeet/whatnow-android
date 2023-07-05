package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class InteractionDtoList(
    @SerializedName("promiseId") val promiseId: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("interactionType") val interactionType: String,
    @SerializedName("count") val count: Int
)