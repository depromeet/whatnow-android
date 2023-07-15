package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.PromisesUsersCreate
import com.google.gson.annotations.SerializedName

data class PromisesUsersCreateResponse(
    @SerializedName("promiseId") val promiseId: Int,
    @SerializedName("mainUserId") val mainUserId: Int,
    @SerializedName("userLocation") val userLocation: CoordinateVoResponse,
    @SerializedName("promiseUserType") val promiseUserType: String,
    @SerializedName("promiseProgress") val promiseProgress: String

)

internal fun PromisesUsersCreateResponse.toDomain() = PromisesUsersCreate(
    promiseId = promiseId,
    mainUserId = mainUserId,
    userLocation = userLocation.toDomain(),
    promiseUserType = promiseUserType,
    promiseProgress = promiseProgress
)
