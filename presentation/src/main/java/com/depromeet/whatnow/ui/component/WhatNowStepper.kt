package com.depromeet.whatnow.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.depromeet.whatnow.ui.theme.White

@Composable
fun WhatNowStepper(
    height: Dp,
    isStart: Boolean = false,
    isEnd: Boolean = false,
    circleOffset: Dp = 16.dp
) {
    val topCorner = if (isStart) STEPPER_BORDER_RADIUS else 0.dp
    val bottomCorner = if (isEnd) STEPPER_BORDER_RADIUS else 0.dp

    Box(
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .height(height)
                .width(STEPPER_WIDTH),
            color = WhatNowTheme.colors.gray100,
            shape = RoundedCornerShape(
                topStart = topCorner,
                topEnd = topCorner,
                bottomStart = bottomCorner,
                bottomEnd = bottomCorner
            )
        ) {}
        Surface(
            modifier = Modifier
                .padding(top = if (isStart) 0.dp else circleOffset)
                .size(28.dp),
            color = White,
            shadowElevation = 1.dp,
            shape = CircleShape
        ) {
            Surface(
                modifier = Modifier
                    .size(14.dp)
                    .padding(7.dp),
                color = WhatNowTheme.colors.gray400,
                shape = CircleShape
            ) {}
        }
    }
}

private val STEPPER_WIDTH = 6.dp
private val STEPPER_BORDER_RADIUS = 100.dp