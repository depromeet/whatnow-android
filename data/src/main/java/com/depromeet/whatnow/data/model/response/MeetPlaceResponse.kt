package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.PlaceVo
import com.google.gson.annotations.SerializedName

data class MeetPlaceResponse(
    @SerializedName("coordinate") val coordinate: CoordinateVoResponse,
    @SerializedName("address") val address: String
)

internal fun MeetPlaceResponse.toDomain() = PlaceVo(
    coordinate = coordinate.toDomain(),
    address = address
)

internal fun PlaceVo.toData() = MeetPlaceResponse(
    coordinate = coordinate.toData(),
    address = address
)
