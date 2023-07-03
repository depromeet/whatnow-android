package com.depromeet.whatnow.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WhatNowMyStatusChangeIndicator(
    modifier: Modifier, pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    val testTitle = listOf<String>("뛰고있어", "걸어가는중", "탐승중")

    Row(
        modifier = modifier.padding(top = 32.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }

            }, modifier = modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_ios),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
        }

        Surface(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .wrapContentWidth()
                .height(40.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(WhatNowTheme.colors.gray100)
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    text = testTitle[pagerState.currentPage],
                    style = WhatNowTheme.typography.body3.copy(
                        fontSize = 14.sp,
                        color = WhatNowTheme.colors.whatNowBlack,
                    ),
                    textAlign = TextAlign.Center
                )
            }

        }


        IconButton(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }, modifier = modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_forward_ios_24),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
        }

    }
}