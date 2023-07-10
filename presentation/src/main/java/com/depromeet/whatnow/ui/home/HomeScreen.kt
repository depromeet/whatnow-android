package com.depromeet.whatnow.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.depromeet.whatnow.ui.promiseAdd.Calendar
import com.depromeet.whatnow.ui.theme.WhatNowTheme


@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val isRefresh by viewModel.isRefresh.collectAsState()
    val launcher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.refresh()
        }
    }
    val uiState by viewModel.uiState.collectAsState()
    val timeOver by viewModel.uiState.value.timeOver.collectAsState()

    val promisesUsersStatus by viewModel.uiState.value.promisesUsersStatus.collectAsState()
    val currentStatus by viewModel.uiState.value.currentStatus.collectAsState()

    val scrollState = rememberScrollState()

    // 스크롤을 위로 땡겼을 때 리로드 되면 좋을듯
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(WhatNowTheme.colors.gray50)
                .verticalScroll(scrollState)
        )
    ) {

        Box {
            /**
             * 홈 활성화 여부와 타임오버에 따라서 맵 변경
             * */

            when (currentStatus) {
                HomeActivateStatus.InActivity -> WhatNowInactivityMap(
                    modifier = Modifier,
                    isPromise = promisesUsersStatus.isNotEmpty(),
                )

                HomeActivateStatus.Activity -> WhatNowActivityMap(
                    modifier = Modifier,
                    context = context,
                    viewModel = viewModel
                )

                else -> WhatNowTimeOverMap(
                    modifier = Modifier,
                    viewModel = viewModel,
                    isLate = currentStatus == HomeActivateStatus.Late
                )
            }

            // 앱바
            WhatNowHomeAppBar(modifier = Modifier)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {

            Log.d("ttttteset", promisesUsersStatus.isEmpty().toString())

            if (promisesUsersStatus.isEmpty()) {
                when (currentStatus) {
                    HomeActivateStatus.InActivity -> {
                        val selectedDate = remember { mutableStateOf("") }

                        // 달력
                        Calendar(
                            onDateChanged = {
                                selectedDate.value = it
                            },
                            onDateData = {}
                        )
                    }

                    else -> {

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
                                tint = if (promisesUsersStatus.isEmpty()) WhatNowTheme.colors.gray400
                                else WhatNowTheme.colors.whatNowBlack


                            )

                        }

                        Image(
                            painter = painterResource(id = R.drawable.home_promise_empty_icon),
                            contentDescription = null
                        )
                    }
                }

            } else {

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
                        tint = if (promisesUsersStatus.isEmpty()) WhatNowTheme.colors.gray400
                        else WhatNowTheme.colors.whatNowBlack
                    )
                }
                WhatNowPromiseList(
                    modifier = Modifier,
                    promisesUsersStatus = promisesUsersStatus
                )
            }
        }
    }
}