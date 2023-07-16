package com.depromeet.whatnow.domain.model

data class PatchUsersProfile(
    val username: String,
    val profileImage: String,
    val isDefaultImg: Boolean,
    val imageKey: String
)
