package com.depromeet.whatnow.data.model.request

import com.google.gson.annotations.SerializedName

data class UsersProfileRequest(
    @SerializedName("profileImage") val profileImage: String,
    @SerializedName("username") val username: String
)
