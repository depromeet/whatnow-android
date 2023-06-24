package com.depromeet.whatnow.ui.model

import java.time.LocalDateTime

data class Promise(
    val title: String,
    val location: String,
    val datetime: LocalDateTime,
    val participants: List<User>,
    val imageUrls: List<String>,
    val highlights: List<Highlight>,
    val timeOverImageUrl: String
)

fun DUMMY_PROMISE(
    title: String = "약속 이름",
    location: String = "약속 장소",
    datetime: LocalDateTime = LocalDateTime.now(),
    participants: List<User> = listOf(
        DUMMY_USER(),
        DUMMY_USER(),
        DUMMY_USER(arrivalState = ArrivalState.Late),
        DUMMY_USER(arrivalState = ArrivalState.Late)
    ),
    imageUrls: List<String> = DUMMY_IMAGES.shuffled(),
    highlights: List<Highlight> = listOf(
        DUMMY_HIGHLIGHT(),
        DUMMY_HIGHLIGHT(type = HighlightType.Arrive, text = "윤여준 도착완료"),
        DUMMY_HIGHLIGHT(),
        DUMMY_HIGHLIGHT(type = HighlightType.Arrive, text = "윤여준 도착완료"),
        DUMMY_HIGHLIGHT()
    ),
    timeOverImageUrl: String = "https://images.unsplash.com/photo-1478860409698-8707f313ee8b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"
) = Promise(title, location, datetime, participants, imageUrls, highlights, timeOverImageUrl)

val DUMMY_IMAGES = listOf(
    "https://images.unsplash.com/photo-1469854523086-cc02fe5d8800?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1642&q=80",
    "https://images.unsplash.com/photo-1499591934245-40b55745b905?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1744&q=80",
    "https://images.unsplash.com/photo-1606054534744-a3b13e35c574?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1050&q=80",
    "https://images.unsplash.com/photo-1446768500601-ac47e5ec3719?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1692&q=80",
    "https://images.unsplash.com/photo-1566999573217-25c859ec1450?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80"
)