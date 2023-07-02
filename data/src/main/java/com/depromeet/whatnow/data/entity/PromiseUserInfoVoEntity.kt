package com.depromeet.whatnow.data.entity

data class PromiseUserInfoVoEntity(
    val profileImg : String,
    val nickname : String,
    val isDefaultImg : Boolean,
    val promiseUserType : PromiseUserEntity.PromiseUserType
)
