package com.depromeet.whatnow.ui.home

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    val isActivate: Boolean = true
    val isTimeOver: Boolean = false
    val isLate: Boolean = false

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhatNowTheme.colors.gray50)
    ) {
        Box {
            /**
             * 홈 활성화 여부와 타임오버에 따라서 맵 변경
             * */
            Surface(onClick = {
                PromiseActivateActivity.startActivity(context = context)
            }) {

                when (uiState.currentStatus) {
                    HomeActivateStatus.InActivate -> WhatNowInactivityMap(modifier = Modifier)
                    HomeActivateStatus.Activate -> WhatNowActivityMap(modifier = Modifier)
                    else -> WhatNowTimeOverMap(modifier = Modifier, isLate = isLate)
                }
            }

            // 앱바
            WhatNowHomeAppBar(modifier = Modifier)
        }
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
//                .verticalScroll(rememberScrollState())
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 17.dp),
                    text = stringResource(R.string.upcoming_promise),
                    style = WhatNowTheme.typography.headline3.copy(
                        fontSize = 20.sp, color = WhatNowTheme.colors.whatNowBlack
                    )
                )

                if (isTimeOver) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 8.dp, bottom = 24.dp),
                        painter = painterResource(id = R.drawable.arrow_forward_ios),
                        contentDescription = null
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .align(Alignment.CenterEnd),
                        painter = painterResource(id = R.drawable.whatnow_home_lcon),
                        contentDescription = null
                    )
                }
            }

            WhatNowPromiseList(
                modifier = Modifier, promises = listOf("test", "test", "test", "test")
            )
        }
    }
}