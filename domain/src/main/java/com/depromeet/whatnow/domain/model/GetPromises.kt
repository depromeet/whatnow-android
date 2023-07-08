package com.depromeet.whatnow.domain.model


data class GetPromises(
    val promiseId: Int,
    val address: String,
    val coordinateVo: CoordinateVo,
    val title: String,
    val endTime: String,
    val users: Users

)