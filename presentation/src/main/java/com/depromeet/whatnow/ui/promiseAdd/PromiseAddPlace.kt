package com.depromeet.whatnow.ui.promiseAdd

data class PromiseAddPlace(
    val placeTitle: String,
    val placeAddress: String,
    // 위도 (mapx)
    val latitude: Double,
    // 경도 (mapy)
    val longitude: Double,
)

data class MapData(
    val latitude: Double,
    val longitude: Double
)