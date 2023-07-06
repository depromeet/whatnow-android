package com.depromeet.whatnow.domain.model


data class Interactions(
    val senderUser: Users,
    val count: Int,
    val interactionType: String
)