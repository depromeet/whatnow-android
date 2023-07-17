package com.depromeet.whatnow.domain.model

data class PromiseDomainResponse(
    val title: String,
    val mainUserId: Int,
    val meetPlace: PlaceVo,
    val endTime: String,
    val inviteCode: String,
)
