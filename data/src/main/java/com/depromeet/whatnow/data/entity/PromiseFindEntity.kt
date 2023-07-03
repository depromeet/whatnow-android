package com.depromeet.whatnow.data.entity

data class PromiseFindEntity(
    val title : String,
    val address : String,
    val endTime : String,
    val users : List<UserInfoVoEntity>
)
