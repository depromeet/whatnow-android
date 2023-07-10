package com.depromeet.whatnow.domain.model


data class UserProgress(
    val user: Users,
    val currentProgress: String,
    val beforeProgress: String
)
