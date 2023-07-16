package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.PostImagesUsersMe

data class PostImagesUsersMeResponse(
    val imageUrl: String
)

fun PostImagesUsersMeResponse.toDomain() = PostImagesUsersMe(
    imageUrl
)
