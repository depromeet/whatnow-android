package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.CoordinateVo
import com.google.gson.annotations.SerializedName

data class CoordinateVoResponse(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)

internal fun CoordinateVoResponse.toDomain() = CoordinateVo(
    latitude = latitude,
    longitude = longitude
)

internal fun CoordinateVo.toData() = CoordinateVoResponse(
    latitude = latitude,
    longitude = longitude
)