package com.depromeet.whatnow.domain.model


data class PromisesUsersLocation(
    val userId: Int,
    val userLocation: CoordinateVo,
    val promiseUserType: String,
)