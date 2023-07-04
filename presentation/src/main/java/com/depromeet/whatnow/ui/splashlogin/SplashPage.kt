package com.depromeet.whatnow.ui.splashlogin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SplashPage(
    visible: Boolean,
    items: List<PageItem>,
    login: () -> Unit,
) {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var lastPage by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                HorizontalPager(
                    count = items.size,
                    state = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) { page ->
                    if (page == 1) {
                        SplashPageItem(items[page], modifier = Modifier, test = true)
                    } else {
                        SplashPageItem(items[page], modifier = Modifier, test = false)
                    }
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    activeColor = WhatNowTheme.colors.gray900,
                    inactiveColor = WhatNowTheme.colors.gray200,
                )
                Spacer(modifier = Modifier.height(20.dp))
                KakaoLoginButton(login)
            }
        }
    )
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .map { it == items.lastIndex }
            .collect { lastPage = it }
    }
}


@Composable
fun KakaoLoginButton(onClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.kakaologin),
        contentDescription = null,
        modifier = Modifier
            .size(width = 326.dp, height = 52.dp)
            .clickable { onClick() }
    )
    Spacer(modifier = Modifier.height(32.dp))
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
fun SplashPagePreview() {
    WhatNowTheme() {
        Surface() {
            SplashPage(
                visible = true,
                items = PageItems,
                login = { },
            )
        }
    }
}
