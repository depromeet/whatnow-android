package com.depromeet.whatnow.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WhatNowTabOtherContent(
    promises: List<Promise>,
    onCreate: () -> Unit
) {
    val pagerState = rememberPagerState()


    Column(
        modifier = Modifier.padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = 4,
            state = pagerState,
            modifier = Modifier.width(400.dp)
        ) {
            WhatNowTabOtherContentCard(modifier = Modifier, promises = promises)
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = WhatNowTheme.colors.whatNowPurple,
            inactiveColor = WhatNowTheme.colors.gray600,
            indicatorWidth = 4.dp,
            indicatorHeight = 4.dp,
            spacing = 4.dp,
            modifier = Modifier
                .padding(top = 9.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            WhatNowEmojiButton(
                modifier = Modifier,
                emojiIconRes = R.drawable.emoji_music_button,
                onClick = { }
            )
            WhatNowEmojiButton(
                modifier = Modifier,
                emojiIconRes = R.drawable.emoji_poop_button,
                onClick = { }
            )
            WhatNowEmojiButton(
                modifier = Modifier,
                emojiIconRes = R.drawable.emoji_heart_button,
                onClick = { }
            )
            WhatNowEmojiButton(
                modifier = Modifier,
                emojiIconRes = R.drawable.emoji_footprint_button,
                onClick = { }
            )
        }
    }
}