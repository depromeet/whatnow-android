package com.depromeet.whatnow.data.entity

data class PromiseUserEntity(
    val promiseId : Int,
    val mainUserId : Int,
    val userLocation : CoordinateVoEntity,
    val promiseUserType : PromiseUserType
){
    enum class PromiseUserType { READY, LATE, WAIT, CANCEL}
}
