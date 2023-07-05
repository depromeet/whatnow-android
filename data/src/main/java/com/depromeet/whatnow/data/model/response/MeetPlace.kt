package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.CoordinateVo
import com.google.gson.annotations.SerializedName

data class MeetPlace(
    @SerializedName("coordinate") val coordinate: CoordinateVo,
    @SerializedName("address") val address: String
)