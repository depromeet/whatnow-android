package com.depromeet.whatnow.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
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

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPager(
                modifier = Modifier,
                state = pagerState,
                pageCount = promises[0].imageUrls.size,
                contentPadding = PaddingValues(horizontal = 22.dp),
                pageSpacing = 8.dp
            ) {
                WhatNowTabOtherContentCard(modifier = Modifier, promises = promises)
            }

            Spacer(modifier = Modifier.height(9.dp))
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = promises[0].imageUrls.size,
                indicatorWidth = 4.dp,
                indicatorHeight = 4.dp,
                indicatorShape = CircleShape,
                activeColor = WhatNowTheme.colors.whatNowPurple,
                inactiveColor = WhatNowTheme.colors.gray600,
                spacing = 4.dp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 22.dp),
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