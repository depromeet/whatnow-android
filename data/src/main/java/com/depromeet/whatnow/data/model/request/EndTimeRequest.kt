package com.depromeet.whatnow.data.model.request

import com.google.gson.annotations.SerializedName

data class EndTimeRequest(
    @SerializedName("endTime") val endTime: String
)
