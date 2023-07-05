package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class PromiseUsers(
    @SerializedName("profileImg") val profileImg: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("isDefaultImg") val isDefaultImg: Boolean,
    @SerializedName("promiseUserType") val promiseUserType: String
)

