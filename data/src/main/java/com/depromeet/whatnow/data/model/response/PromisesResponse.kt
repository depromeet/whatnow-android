package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.PromiseDomainResponse
import com.google.gson.annotations.SerializedName

data class PromisesResponse(
    @SerializedName("title") val title: String,
    @SerializedName("mainUserId") val mainUserId: Int,
    @SerializedName("meetPlace") val meetPlace: MeetPlaceResponse,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("inviteCode") val inviteCode: String,
)

internal fun PromisesResponse.toDomain() = PromiseDomainResponse(
    title = title,
    mainUserId = mainUserId,
    meetPlace = meetPlace.toDomain(),
    endTime = endTime,
    inviteCode = inviteCode
)