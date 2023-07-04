package com.depromeet.whatnow.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.DUMMY_PROMISE
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun WhatNowMyStatusChange(
    onCreate: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 3 })

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HorizontalPager(
                state = pagerState,
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(horizontal = 116.34.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = DUMMY_PROMISE().participants[pagerState.currentPage].profileImageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(128.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .border(
                                width = 2.dp,
                                color = WhatNowTheme.colors.gray50,
                                shape = RoundedCornerShape(24.dp)
                            )
                    )

                    Image(
                        modifier = Modifier.size(200.dp),
                        painter = painterResource(R.drawable.status_change_washing_img),
                        contentDescription = null
                    )
                }
            }

            WhatNowMyStatusChangeIndicator(
                modifier = Modifier,
                pagerState = pagerState,
            )
        }
    }
}