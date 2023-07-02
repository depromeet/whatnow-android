package com.depromeet.whatnow.data.entity

data class InteractionDetailEntity(
    val senderUser : UserInfoVoEntity,
    val count : Int,
    val interactionType : InteractionEntity.InteractionType
)
