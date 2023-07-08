package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.CoordinateVo
import com.google.gson.annotations.SerializedName

data class GetPromisesResponse(
    @SerializedName("promiseId") val promiseId: Int,
    @SerializedName("address") val address: String,
    @SerializedName("coordinateVo") val coordinateVo: CoordinateVo,
    @SerializedName("title") val title: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("users") val users: UsersResponse

)