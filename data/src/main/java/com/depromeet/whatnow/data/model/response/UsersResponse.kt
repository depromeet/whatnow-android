package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("profileImg") val profileImg: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("isDefaultImg") val isDefaultImg: Boolean
)