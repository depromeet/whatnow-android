package com.depromeet.whatnow.domain.model

data class GetImagePresignedUrl(
    val presignedUrl: String,
    val key: String
)
