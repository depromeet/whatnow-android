package com.depromeet.whatnow.domain.model


data class GetPromisesProgress(
    val group: String,
    val progresses: List<String>
)