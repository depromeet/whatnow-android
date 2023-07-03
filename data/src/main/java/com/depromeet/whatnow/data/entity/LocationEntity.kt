package com.depromeet.whatnow.data.entity

import com.depromeet.whatnow.domain.model.Location

data class LocationEntity(
    val location : String
)

internal fun Location.toData() : LocationEntity = LocationEntity(
    location = location
)