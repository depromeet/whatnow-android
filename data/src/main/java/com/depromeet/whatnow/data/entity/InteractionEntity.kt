package com.depromeet.whatnow.data.entity

data class InteractionEntity(
    val promiseId : Int,
    val userId : Int,
    val interactionType : InteractionType,
    val count : Int
){
    enum class InteractionType { MUSIC, POOP, HEART, STEP}
}
