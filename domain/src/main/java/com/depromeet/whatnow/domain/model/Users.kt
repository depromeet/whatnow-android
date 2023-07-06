package com.depromeet.whatnow.domain.model

data class Users(
    val id: Int,
    val profileImg: String,
    val nickname: String,
    val isDefaultImg: Boolean
)
