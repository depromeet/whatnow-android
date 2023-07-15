package com.depromeet.whatnow.ui.model

import com.depromeet.whatnow.domain.model.GetPromisesUsersStatus
import java.io.Serializable
import java.time.LocalDateTime
import kotlin.random.Random

data class Promise(
    val title: String,
    val location: String,
    val datetime: LocalDateTime,
    val participants: List<User>,
    val imageUrls: List<String>,
    val highlights: List<Highlight>,
    val timeOverImageUrl: String,
    val lat: Double = 0.0,
    val lng: Double = 0.0
) : Serializable

fun GetPromisesUsersStatus.toUiModel() = Promise(
    title = title,
    location = address,
    datetime = LocalDateTime.parse(endTime),
    participants = promiseUsers.map { it.toUiModel() },
    imageUrls = promiseImageUrls,
    lat = coordinateVo.latitude,
    lng = coordinateVo.longitude,
    timeOverImageUrl = "",
    highlights = emptyList()
)

fun DUMMY_PROMISE(
    title: String = "약속 이름",
    location: String = "약속 장소",
    datetime: LocalDateTime = LocalDateTime.now().minusDays(Random.nextLong(100)),
    participants: List<User> = listOf(
        DUMMY_USER(),
        DUMMY_USER(),
        DUMMY_USER(arrivalState = ArrivalState.LATE),
        DUMMY_USER(arrivalState = ArrivalState.LATE)
    ),
    imageUrls: List<String> = DUMMY_IMAGES.shuffled().take(Random.nextInt(3, 6)),
    highlights: List<Highlight> = listOf(
        DUMMY_HIGHLIGHT(),
        DUMMY_HIGHLIGHT(type = HighlightType.Arrive, text = "윤여준 도착완료"),
        DUMMY_HIGHLIGHT(),
        DUMMY_HIGHLIGHT(type = HighlightType.Arrive, text = "윤여준 도착완료"),
        DUMMY_HIGHLIGHT()
    ),
    timeOverImageUrl: String = DUMMY_IMAGES.shuffled().first()
) = Promise(title, location, datetime, participants, imageUrls, highlights, timeOverImageUrl)

val DUMMY_IMAGES = listOf(
    "https://images.unsplash.com/photo-1469854523086-cc02fe5d8800?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1642&q=80",
    "https://images.unsplash.com/photo-1440615496174-ee7ecbe8e733?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1160&q=80",
    "https://images.unsplash.com/photo-1419133126304-d17b34c34d76?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80",
    "https://images.unsplash.com/photo-1446768500601-ac47e5ec3719?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1692&q=80",
    "https://images.unsplash.com/photo-1566999573217-25c859ec1450?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80",
    "https://images.unsplash.com/photo-1584064149621-aa938221737e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1548&q=80",
    "https://images.unsplash.com/photo-1555881400-74d7acaacd8b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80",
    "https://images.unsplash.com/photo-1534457099760-2d3e4b45f553?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1169&q=80",
    "https://images.unsplash.com/photo-1590002893334-a9c388ca0c79?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80",
    "https://images.unsplash.com/photo-1531263060782-b024de9b9793?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80",
    "https://images.unsplash.com/photo-1614844848029-058f34a0508b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1287&q=80",
    "https://images.unsplash.com/photo-1518285384707-4829d8e89071?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1287&q=80",
    "https://images.unsplash.com/photo-1566330345336-74084ab7ce8a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1335&q=80",
    "https://images.unsplash.com/photo-1560568380-a552d32e51ee?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1287&q=80"

)