package com.depromeet.whatnow.domain.model

data class GetPromisesUsersStatus(
    val promiseId: Int,
    val address: String,
    val coordinateVo: CoordinateVo,
    val title: String,
    val endTime: String,
    val promiseUsers: List<PromiseUsers>,
    val promiseImageUrls: List<String>,
    val timeOverLocations: List<TimeOverLocations>
)