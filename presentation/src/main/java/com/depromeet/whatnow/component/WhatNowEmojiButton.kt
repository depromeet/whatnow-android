package com.depromeet.whatnow.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowEmojiButton(
    modifier: Modifier, @DrawableRes emojiIconRes: Int, onClick: () -> Unit

) {
    Box(
        modifier = modifier
            .padding(start = 12.dp, end = 12.dp)
    ) {
        Surface(
            onClick = onClick,
            modifier = Modifier
                .padding(top = 2.dp)
                .width(60.dp)
                .height(58.dp),
            shape = RoundedCornerShape(20.dp),
        ) {
            Box(
                modifier = modifier.background(
                    Color(0xff47499F)
                )

            ) {
            }
        }

        Surface(
            onClick = onClick,
            modifier = Modifier
                .width(60.dp)
                .height(54.dp),
            shape = RoundedCornerShape(20.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.background(WhatNowTheme.colors.whatNowPurple)
            ) {
                Icon(
                    painter = painterResource(id = emojiIconRes),
//                    modifier = modifier
//                        .width(28.8.dp)
//                        .height(29.55.dp),
                    contentDescription = null,
                    tint = Color.White,
                )

            }
        }
    }

}