package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.CoordinateVo
import com.google.gson.annotations.SerializedName

data class TimeOverLocationsResponse(
    @SerializedName("userId") val userId: Int,
    @SerializedName("coordinateVo") val coordinateVo: CoordinateVo
)