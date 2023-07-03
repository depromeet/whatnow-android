package com.depromeet.whatnow.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowProfile(
    promise: Promise,
    profileImageSize: Dp,
    statusImageSize: Dp
) {
    Box(contentAlignment = Alignment.Center) {
        AsyncImage(
            model = promise.participants[0].profileImageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(profileImageSize)
                .clip(RoundedCornerShape(24.dp))
                .border(
                    width = 2.dp,
                    color = WhatNowTheme.colors.gray50,
                    shape = RoundedCornerShape(24.dp)
                )
        )

        Image(
            modifier = Modifier.size(statusImageSize),
            painter = painterResource(R.drawable.status_change_washing_img),
            contentDescription = null
        )
    }
}
