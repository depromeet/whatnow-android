package com.depromeet.whatnow.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: T,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timeStamp")
    val timeStamp: String,
)
