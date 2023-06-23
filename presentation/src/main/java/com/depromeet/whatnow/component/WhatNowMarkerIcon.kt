package com.depromeet.whatnow.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.depromeet.whatnow.ui.theme.WhatNowTheme


@Composable
fun WhatNowMarkerIcon() {
    AsyncImage(
        model = "https://media.licdn.com/dms/image/C5603AQHcoKPU9alW9w/profile-displayphoto-shrink_800_800/0/1644498344282?e=1692230400&v=beta&t=aK3Qau7_xpiie2xqI5hulE4H8iEbAcVZPnUXBe7-t6E",
        contentDescription = null,
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = 2.dp,
                color = WhatNowTheme.colors.gray50,
                shape = RoundedCornerShape(24.dp)
            )
    )
}