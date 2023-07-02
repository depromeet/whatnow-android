package com.depromeet.whatnow.ui.home

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.depromeet.whatnow.component.WhatNowActivityMap
import com.depromeet.whatnow.component.WhatNowHomeAppBar
import com.depromeet.whatnow.component.WhatNowInactivityMap
import com.depromeet.whatnow.component.WhatNowPromiseList
import com.depromeet.whatnow.component.WhatNowTimeOverMap
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.promiseActivate.PromiseActivateActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val isRefresh by viewModel.isRefresh.collectAsState()
    val launcher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.refresh()
        }
    }
    val uiState by viewModel.uiState.collectAsState()
    val promise = listOf("test", "test", "test", "test", "test", "test", "test")
    val scrollState = rememberScrollState()

    // 스크롤을 위로 땡겼을 때 리로드 되면 좋을듯
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhatNowTheme.colors.gray50)
            .verticalScroll(scrollState)
    ) {
        Box {
            /**
             * 홈 활성화 여부와 타임오버에 따라서 맵 변경
             * */
            Surface(onClick = {
                PromiseActivateActivity.startActivity(context = context)
            }) {

                when (uiState.currentStatus) {
                    HomeActivateStatus.InActivity -> WhatNowInactivityMap(
                        modifier = Modifier,
                        isPromise = promise.isNotEmpty()
                    )

                    HomeActivateStatus.Activity -> WhatNowActivityMap(modifier = Modifier)
                    else -> WhatNowTimeOverMap(
                        modifier = Modifier,
                        isLate = uiState.currentStatus == HomeActivateStatus.Late
                    )
                }
            }

            // 앱바
            WhatNowHomeAppBar(modifier = Modifier)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 17.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.upcoming_promise),
                    style = WhatNowTheme.typography.headline3.copy(
                        fontSize = 20.sp, color = WhatNowTheme.colors.whatNowBlack
                    )
                )
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { },
                    painter = painterResource(id = R.drawable.arrow_forward_ios_24),
                    contentDescription = null,
                    tint = if (promise.isEmpty()) WhatNowTheme.colors.gray400
                    else WhatNowTheme.colors.whatNowBlack


                )

            }

            if (promise.isEmpty()) {
                when (uiState.currentStatus) {
                    HomeActivateStatus.Activity -> Image(
                        painter = painterResource(id = R.drawable.home_promise_empty_icon),
                        contentDescription = null
                    )

                    else -> {
                        // 달력
                    }
                }

            } else {
                WhatNowPromiseList(
                    modifier = Modifier, promises = promise
                )
            }
        }
    }
}