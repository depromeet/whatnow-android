package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.GetImagePresignedUrl

data class GetImagePresignedUrlResponse(
    val presignedUrl: String,
    val key: String
)

fun GetImagePresignedUrlResponse.toDomain() = GetImagePresignedUrl(
    presignedUrl, key
)
