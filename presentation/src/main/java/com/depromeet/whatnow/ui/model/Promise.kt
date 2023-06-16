package com.depromeet.whatnow.ui.model

import java.time.LocalDateTime

data class Promise(
    val title: String,
    val location: String,
    val datetime: LocalDateTime,
    val participants: List<User>,
    val imageUrls: List<String>
)

fun DUMMY_PROMISE(
    title: String = "약속 이름",
    location: String = "약속 장소",
    datetime: LocalDateTime = LocalDateTime.now(),
    participants: List<User> = List(5) { DUMMY_USER() },
    imageUrls: List<String> = listOf("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgD57qOWeCNuSagChKRfqBBNn3IN1j7SEbohUhWXXzEwtsM6m9uHkzv_N5yCsBcLDhJhDV1J-7Omwf9vFe7DP31UNxnFzooj8JWgL9DRNvgHuoF7BtnKl38yqoZ5NGR0AnkXxeOO1-XCm8jzwtqbHhooKkqLZg28idBo1QHhGWXaAk3vOtHpAzQjhUw/s1600/ADS_22%20-%20What_s%20new%20in%20Jetpack%20Compose%20ADS22-SOCIAL.png")
) = Promise(title, location, datetime, participants, imageUrls)
