package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class PromisesResponse(
    @SerializedName("title") val title: String,
    @SerializedName("mainUserId") val mainUserId: Int,
    @SerializedName("meetPlace") val meetPlace: MeetPlaceResponse,
    @SerializedName("endTime") val endTime: String
)

