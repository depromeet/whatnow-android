package com.depromeet.whatnow.data.model.request

import com.depromeet.whatnow.domain.model.PatchUsersProfile

data class PatchUsersProfileRequest(
    val username: String,
    val profileImage: String,
    val isDefaultImg: Boolean,
    val imageKey: String
)

fun PatchUsersProfileRequest.toDomain() =
    PatchUsersProfile(username, profileImage, isDefaultImg, imageKey)
