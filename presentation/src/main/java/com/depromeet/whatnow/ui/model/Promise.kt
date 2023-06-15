package com.depromeet.whatnow.ui.model

import java.time.LocalDateTime

data class Promise(
    val title: String,
    val location: String,
    val datetime: LocalDateTime,
    val participants: List<User>
)
