package com.depromeet.whatnow.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowProfile(
    promise: Promise,
    size: Dp
) {
    AsyncImage(
        model = promise.participants[0].profileImageUrl,
        contentDescription = null,
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = 2.dp,
                color = WhatNowTheme.colors.gray50,
                shape = RoundedCornerShape(24.dp)
            )
    )
}
