package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.CoordinateVo
import com.google.gson.annotations.SerializedName

data class GetPromisesUsersStatusResponse(
    @SerializedName("promiseId") val promiseId: Int,
    @SerializedName("address") val address: String,
    @SerializedName("coordinateVo") val coordinateVo: CoordinateVo,
    @SerializedName("title") val title: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("promiseUsers") val promiseUsers: List<PromiseUsersResponse>,
    @SerializedName("promiseImageUrls") val promiseImageUrls: List<String>,
    @SerializedName("timeOverLocations") val timeOverLocations: List<TimeOverLocationsResponse>
)