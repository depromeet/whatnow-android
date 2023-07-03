package com.depromeet.whatnow.data.entity

data class PromiseDetailEntity(
    val title: String,
    val date: String,
    val promiseUsers: List<PromiseUserInfoVoEntity>,
    val promiseImageUrls: List<String>,
    val timeOverLocations: List<LocationCaptureEntity>,
)
