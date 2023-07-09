package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.PlaceVo
import com.google.gson.annotations.SerializedName

data class MeetPlaceResponse(
    @SerializedName("coordinate") val coordinate: CoordinateVoResponse,
    @SerializedName("address") val address: String
)

interface fun PlaceVo.toDomain() = MeetPlaceResponse(
    coordinate = coordinate.toDomain(),
    address = address
)