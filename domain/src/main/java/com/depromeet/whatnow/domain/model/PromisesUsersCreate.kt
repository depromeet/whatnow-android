package com.depromeet.whatnow.domain.model

data class PromisesUsersCreate(
    val promiseId: Int,
    val mainUserId: Int,
    val userLocation: CoordinateVo,
    val promiseUserType: String,
    val promiseProgress: String
)
