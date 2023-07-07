package com.depromeet.whatnow.data.model.request

import com.depromeet.whatnow.data.entity.LocationEntity
import com.depromeet.whatnow.domain.model.Location
import com.google.gson.annotations.SerializedName

data class LocationRequest(
    @SerializedName("location")
    val location: String,
)

fun LocationRequest.toDomain() = Location(
    location = location
)

internal fun Location.toData() = LocationRequest(
    location = location
)
