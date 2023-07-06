package com.depromeet.whatnow.domain.model


data class PromisesProgress(
    val User: Users,
    val currentProgress: String,
    val beforeProgress: String
)
