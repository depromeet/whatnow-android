package com.depromeet.whatnow.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowEmojiStats(modifier: Modifier, @DrawableRes emojiIconRes: Int, count: Int) {
    Surface(
        modifier = Modifier
            .width(76.dp)
            .height(32.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 1.dp, color = WhatNowTheme.colors.gray50
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.background(WhatNowTheme.colors.whatNowPurple)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.5.dp, end = 10.5.dp),
                horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = emojiIconRes),
                    contentDescription = null,
                    tint = Color.White,
                )

                Text(
                    modifier = modifier.width(27.dp),
                    text = count.toString(), style = WhatNowTheme.typography.body3.copy(
                        fontSize = 14.sp,
                        color = WhatNowTheme.colors.gray50,
                    ), textAlign = TextAlign.Center,
                    maxLines = 1, overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}