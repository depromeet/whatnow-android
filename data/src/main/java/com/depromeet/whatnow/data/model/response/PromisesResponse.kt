package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.Promise
import com.google.gson.annotations.SerializedName

data class PromisesResponse(
    @SerializedName("title") val title: String,
    @SerializedName("mainUserId") val mainUserId: Int,
    @SerializedName("meetPlace") val meetPlace: MeetPlaceResponse,
    @SerializedName("endTime") val endTime: String
)

internal fun PromisesResponse.toDomain() = Promise(
    title = title,
    mainUserId = mainUserId,
    meetPlace = meetPlace.toDomain(),
    endTime = endTime
)

//internal fun Promise.toData() = PromisesResponse(
//    title = title,
//    mainUserId = mainUserId,
//    meetPlace = meetPlace.toData(),
//    endTime = endTime
//)