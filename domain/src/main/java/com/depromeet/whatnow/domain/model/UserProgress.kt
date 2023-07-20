package com.depromeet.whatnow.domain.model


data class UserProgress(
    val user: Users,
    val currentProgress: CurrentProgress,
    val beforeProgress: String?
)

data class CurrentProgress(
    val progressGroup: String,
    val kr: String,
    val code: String,
    val image: String,
)