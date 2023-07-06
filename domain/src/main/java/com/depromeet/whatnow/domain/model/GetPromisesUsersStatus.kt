package com.depromeet.whatnow.domain.model

data class GetPromisesUsersStatus(

    val title: String,
    val date: String,
    val promiseUsers: List<PromiseUsers>,
    val promiseImageUrls: List<String>,
    val timeOverLocations: List<TimeOverLocations>
)