package com.depromeet.whatnow.domain.model


data class InteractionDtoList(
    val promiseId: Int,
    val userId: Int,
    val interactionType: String,
    val count: Int
)