package com.depromeet.whatnow.ui.splashlogin

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.dialog.RegisterAgreeDialog
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SplashPage(
    viewModel: SplashViewModel,
    visible: Boolean,
    items: List<PageItem>,
    login: () -> Unit,
) {
    val context = LocalContext.current
    val showDialog by viewModel.registerAgreePopup.collectAsState()
    val pagerState = rememberPagerState()
    var lastPage by remember { mutableStateOf(false) }
    // 약관 팝업 노션 URL
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://kdomo.notion.site/cdb0189ba5f14cf5837e7667e1529e2e")
    )

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
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp, bottom = 17.dp),
                    activeColor = WhatNowTheme.colors.gray900,
                    inactiveColor = WhatNowTheme.colors.gray200,
                )

                KakaoLoginButton(login)

            }
        }
    )

    if (showDialog) {
        RegisterAgreeDialog(
            onDismiss = { viewModel.shownRegisterAgree() },
            okClick = { context.startActivity(intent) })
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .map { it == items.lastIndex }
            .collect { lastPage = it }
    }
}


@Composable
fun KakaoLoginButton(onClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.kakaologinlogo),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp,0.dp,16.dp,32.dp)
            .aspectRatio(328/52f)

            .clickable { onClick() }
    )
}