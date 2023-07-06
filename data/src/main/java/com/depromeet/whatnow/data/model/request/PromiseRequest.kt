package com.depromeet.whatnow.data.model.request

import com.depromeet.whatnow.data.model.response.MeetPlaceResponse
import com.google.gson.annotations.SerializedName

data class PromiseRequest(
    @SerializedName("title") val title: String,
    @SerializedName("mainUserId") val mainUserId: Int,
    @SerializedName("meetPlace") val meetPlace: MeetPlaceResponse,
    @SerializedName("endTime") val endTime: String,

    )
