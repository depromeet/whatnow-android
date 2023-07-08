package com.depromeet.whatnow.domain.model


data class PromisesUsersSeparated(
    val promiseId: Int,
    val address: String,
    val coordinateVo: CoordinateVo,
    val title: String,
    val endTime: String,
    val users: Users
)


