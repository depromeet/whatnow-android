package com.depromeet.whatnow.ui.promiseAdd

data class PromiseAddPlace(
    val placeTitle: String,
    val placeAddress: String,
)

fun DUMMY_PLACE(
    placeTitle: String = "장소 이름",
    placeAddress: String = "장소 주소",
) = PromiseAddPlace(placeTitle, placeAddress)