package com.depromeet.whatnow.data.model.response

import com.google.gson.annotations.SerializedName

data class GetPromisesUsersStatus(
    @SerializedName("title") val title: String,
    @SerializedName("date") val date: String,
    @SerializedName("promiseUsers") val promiseUsers: List<PromiseUsersResponse>,
    @SerializedName("promiseImageUrls") val promiseImageUrls: List<String>,
    @SerializedName("timeOverLocations") val timeOverLocations: List<TimeOverLocationsResponse>
)