package com.depromeet.whatnow.data.model.request

import com.depromeet.whatnow.data.entity.LocationEntity
import com.google.gson.annotations.SerializedName

data class LocationRequest(
    @SerializedName("location")
    val location: String,
)

fun LocationEntity.toData(): LocationRequest = LocationRequest(
    location = location
)