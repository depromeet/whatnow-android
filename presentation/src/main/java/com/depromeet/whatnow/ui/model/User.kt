package com.depromeet.whatnow.ui.model

data class User(
    val name: String,
    val profileImageUrl: String
)

fun DUMMY_USER(
    name: String = "사용자",
    profileImageUrl: String = "https://media.licdn.com/dms/image/C5603AQHcoKPU9alW9w/profile-displayphoto-shrink_800_800/0/1644498344282?e=1692230400&v=beta&t=aK3Qau7_xpiie2xqI5hulE4H8iEbAcVZPnUXBe7-t6E"
) = User(name, profileImageUrl)
