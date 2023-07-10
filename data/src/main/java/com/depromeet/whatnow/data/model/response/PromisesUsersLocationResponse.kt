package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.CoordinateVo
import com.google.gson.annotations.SerializedName

data class PromisesUsersLocationResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("userLocation") val userLocation: CoordinateVo,
    @SerializedName("promiseUserType") val promiseUserType: String,
)