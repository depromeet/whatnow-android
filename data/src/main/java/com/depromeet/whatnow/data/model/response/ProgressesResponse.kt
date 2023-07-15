package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class ProgressesResponse(
    @SerializedName("progressGroup") val progressGroup: String,
    @SerializedName("kr") val kr: String,
    @SerializedName("code") val code: String,
    @SerializedName("image") val image: String

)