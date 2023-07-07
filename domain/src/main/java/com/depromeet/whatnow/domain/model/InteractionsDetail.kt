package com.depromeet.whatnow.domain.model


data class InteractionsDetail(
    val senderUser: Users,
    val count: Int,
    val interactionType: String
)