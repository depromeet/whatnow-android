package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class PromisesUsersSeparatedResponse(
    @SerializedName("title") val title: String,
    @SerializedName("address") val address: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("users") val users: UsersResponse
)


