package com.depromeet.whatnow.data.entity

data class PromiseEntity (
    val title : String,
    val mainUserId : Int,
    val meetPlace : PlaceVoEntity,
    val endTime : String
)