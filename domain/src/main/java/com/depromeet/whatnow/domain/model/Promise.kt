package com.depromeet.whatnow.domain.model

data class Promise (
    val title : String,
    val mainUserId : Int,
    val meetPlace : PlaceVo,
    val endTime : String
)