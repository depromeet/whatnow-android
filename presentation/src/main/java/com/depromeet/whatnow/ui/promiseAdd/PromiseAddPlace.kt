package com.depromeet.whatnow.ui.promiseAdd

data class PromiseAddPlace(
    val placeTitle: String,
    val placeAddress: String,
    // 위도 (mapx)
    val latitude : Double,
    // 경도 (mapy)
    val longitude : Double,
)

data class PromiseInfo(
    val placeAddress : String,

)

fun DUMMY_PLACE(
    placeTitle: String = "장소 이름",
    placeAddress: String = "장소 주소",
) = PromiseAddPlace(placeTitle, placeAddress)