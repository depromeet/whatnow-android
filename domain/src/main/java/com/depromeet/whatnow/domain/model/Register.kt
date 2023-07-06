package com.depromeet.whatnow.domain.model

data class Register(
    val profileImage: String,
    val isDefaultImage: Boolean,
    val nickName: String,
    val fcmToken: String,
    val appAlarm: Boolean,
)