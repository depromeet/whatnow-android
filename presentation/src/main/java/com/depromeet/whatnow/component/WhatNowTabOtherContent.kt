package com.depromeet.whatnow.component

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.promiseActivate.PromiseActivateViewModel
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun WhatNowTabOtherContent(
    viewModel: PromiseActivateViewModel,
    onCreate: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val promise by viewModel.uiState.value.promise.collectAsState()
    val promisesUsersStatusList by viewModel.uiState.value.promisesUsersStatusList.collectAsState()
//    val pagerState = rememberPagerState(pageCount = { uiState.promise!!.users.size })
    val pagerState = rememberPagerState(pageCount = { promise!!.users.size - 1 })

    Column(
        modifier = Modifier.padding(top = 16.dp),
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPager(
                modifier = Modifier,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 22.dp),
                pageSpacing = 8.dp
            ) {

                WhatNowTabOtherContentCard(modifier = Modifier, users = promise!!.users[it + 1])

            }

            Spacer(modifier = Modifier.height(9.dp))
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = promise!!.users.size - 1,
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
                .fillMaxSize()
                .padding(top = 12.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WhatNowEmojiButton(
                modifier = Modifier,
                emojiIconRes = R.drawable.emoji_music_button,
                onClick = {
//                    viewModel.sendEmoji("MUSIC",
//                        promisesUsersStatusList!![pagerState.currentPage].mainUserId)
                }
            )
            WhatNowEmojiButton(
                modifier = Modifier,
                emojiIconRes = R.drawable.emoji_poop_button,
                onClick = {
//                    viewModel.sendEmoji("POOP",
//                        promisesUsersStatusList!![pagerState.currentPage].mainUserId)
                }
            )
            WhatNowEmojiButton(
                modifier = Modifier,
                emojiIconRes = R.drawable.emoji_heart_button,
                onClick = {
//                    viewModel.sendEmoji("HEART",
//                        promisesUsersStatusList!![pagerState.currentPage].mainUserId)
                }
            )
            WhatNowEmojiButton(
                modifier = Modifier,
                emojiIconRes = R.drawable.emoji_footprint_button,
                onClick = {
//                    viewModel.sendEmoji("STEP",
//                        promisesUsersStatusList!![pagerState.currentPage].mainUserId)
                }
            )
        }
    }
}

@Composable
fun EmptyWhatNowTabOtherContent() {
    Column(
        modifier = Modifier.padding(top = 16.dp),
    ) {
        Text(text = "친구가 없습니다...")
    }

}