package com.depromeet.whatnow.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowTabMyContent(
    modifier: Modifier,
    promises: List<Promise>,

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp, start = 16.dp, end = 16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                WhatNowProfile(promise = promises[0])
                Column(modifier = modifier.padding(start = 22.dp)) {
                    Text(
                        text = promises[0].participants[0].name,
                        style = WhatNowTheme.typography.body1.copy(
                            fontSize = 18.sp, color = WhatNowTheme.colors.gray50
                        )
                    )
                    Row(
                        modifier = modifier.padding(top = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.state_icon),
                            contentDescription = null,
                        )
                        Text(
                            text = "씻는중",
                            modifier = modifier.padding(start = 3.67.dp),
                            style = WhatNowTheme.typography.caption2.copy(
                                fontSize = 14.sp, color = WhatNowTheme.colors.gray400
                            )
                        )
                    }

                }
            }
            Surface(
                modifier = Modifier
                    .width(84.dp)
                    .height(40.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.background(WhatNowTheme.colors.whatNowPurple)
                ) {
                    Text(
                        text = stringResource(R.string.state_change),
                        style = WhatNowTheme.typography.body3.copy(
                            fontSize = 14.sp,
                            color = Color.White,
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WhatNowEmojiStats(modifier = modifier, emojiIconRes = R.drawable.emoji_music, 31)
            WhatNowEmojiStats(modifier = modifier, emojiIconRes = R.drawable.emoji_poop, 1)
            WhatNowEmojiStats(modifier = modifier, emojiIconRes = R.drawable.emoji_heart, 130)
            WhatNowEmojiStats(modifier = modifier, emojiIconRes = R.drawable.emoji_footprint, 0)

        }

        Box(
            modifier = modifier
                .padding(top = 16.dp, end = 16.dp)
                .align(Alignment.End)
        )
        {
            WhatNowImageStack(imageUrls = promises[0].participants.map { it.profileImageUrl })

        }


    }
}