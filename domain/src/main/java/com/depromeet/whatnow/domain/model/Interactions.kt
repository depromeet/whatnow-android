package com.depromeet.whatnow.domain.model


data class Interactions(
    val promiseId: Int,
    val userId: Int,
    val interactionType: String,
    val count: Int

)