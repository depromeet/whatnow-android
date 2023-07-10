package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class UserProgressResponse(
    @SerializedName("user") val user: UsersResponse,
    @SerializedName("currentProgress") val currentProgress: String,
    @SerializedName("beforeProgress") val beforeProgress: String
)
