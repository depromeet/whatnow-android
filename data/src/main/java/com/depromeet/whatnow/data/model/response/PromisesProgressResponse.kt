package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class PromisesProgressResponse(
    @SerializedName("User") val User: UsersResponse,
    @SerializedName("currentProgress") val currentProgress: String,
    @SerializedName("beforeProgress") val beforeProgress: String
)
