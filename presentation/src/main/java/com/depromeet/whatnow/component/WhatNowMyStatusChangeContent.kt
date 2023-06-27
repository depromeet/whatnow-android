package com.depromeet.whatnow.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.model.ArrivalState
import com.depromeet.whatnow.ui.model.DUMMY_HIGHLIGHT
import com.depromeet.whatnow.ui.model.DUMMY_IMAGES
import com.depromeet.whatnow.ui.model.DUMMY_USER
import com.depromeet.whatnow.ui.model.Highlight
import com.depromeet.whatnow.ui.model.HighlightType
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.model.User
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import java.time.LocalDateTime
import kotlin.random.Random

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WhatNowMyStatusChangeContent(
) {
    Box() {
        WhatNowProfile(promise = DUMMY_PROMISE(), size = 128.dp)
    }
}


fun DUMMY_PROMISE(
    title: String = "약속 이름",
    location: String = "약속 장소",
    datetime: LocalDateTime = LocalDateTime.now().minusDays(Random.nextLong(100)),
    participants: List<User> = listOf(
        DUMMY_USER(),
        DUMMY_USER(),
        DUMMY_USER(arrivalState = ArrivalState.Late),
        DUMMY_USER(arrivalState = ArrivalState.Late)
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