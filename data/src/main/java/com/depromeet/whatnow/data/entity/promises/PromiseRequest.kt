package com.depromeet.whatnow.data.entity.promises

data class PromiseRequest (
    val title : String,
    val mainUserId : Int,
    val meetPlace : PlaceVo,
    val endTime : String
)