package com.depromeet.whatnow.ui.model

import java.time.LocalDateTime

data class Promise(
    val title: String,
    val location: String,
    val datetime: LocalDateTime,
    val participants: List<User>
)

fun DUMMY_PROMISE(
    title: String = "약속 이름",
    location: String = "약속 장소",
    datetime: LocalDateTime = LocalDateTime.now(),
    participants: List<User> = List(5) { DUMMY_USER() }
) = Promise(title, location, datetime, participants)
