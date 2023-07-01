package com.depromeet.whatnow.ui.promiseActivate.myStatus

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.component.WhatNowMyStatusChange
import com.depromeet.whatnow.component.WhatNowNaverMapIconButton
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.promiseActivate.PromiseActivateViewModel
import com.depromeet.whatnow.ui.theme.WhatNowTheme


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MyStatusScreen(
    viewModel: PromiseActivateViewModel,
) {
    val context = LocalContext.current
    val isRefresh by viewModel.isRefresh.collectAsState()
    val launcher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.refresh()
        }
    }

    val isClickedMyStatusCategory by viewModel.isClickedMyStatusCategory.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff1F2128).copy(0.9f))
            .clickable(enabled = false, onClick = {})
    ) {

        WhatNowNaverMapIconButton(modifier = Modifier,
            iconButtonRes = R.drawable.close,
            PaddingValues(start = 16.dp, top = 24.dp),
            alignment = Alignment.TopStart,
            color = Color(0xFFF9F9F9),
            tint = Color.Black,
            onClick = {
                if (!isClickedMyStatusCategory) viewModel.onClickedMyStatus()
                else viewModel.onClickedMyStatusCategory()
            })

        Text(
            modifier = Modifier
                .padding(top = 28.dp)
                .align(Alignment.TopCenter),
            text = stringResource(R.string.my_state_change),
            style = WhatNowTheme.typography.headline2.copy(
                fontSize = 22.sp, color = Color.White
            )
        )

        Row(
            modifier = Modifier
                .padding(top = 100.dp)
                .align(Alignment.TopCenter)
                .clickable { viewModel.onClickedMyStatusCategory() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = viewModel.myStatusCategoryTitle.value,
                style = if (!isClickedMyStatusCategory) WhatNowTheme.typography.headline3.copy(
                    fontSize = 20.sp, color = Color.White
                ) else WhatNowTheme.typography.headline2.copy(
                    fontSize = 22.sp, color = Color.White
                )
            )

            Icon(
                modifier = Modifier.padding(start = 4.dp),
                painter = painterResource(id = R.drawable.expand_more),
                contentDescription = null
            )
        }

        if (!isClickedMyStatusCategory) {


            Box(modifier = Modifier) {
                WhatNowMyStatusChange(onCreate = {})

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(bottom = 120.dp)
                        .align(alignment = Alignment.BottomCenter)
                        .clip(shape = RoundedCornerShape(22.dp))
                        .size(64.dp)
                        .background(WhatNowTheme.colors.whatNowPurple)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.done),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = Color.White
                    )
                }
            }
        } else {

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 160.dp)
            ) {

                Text(
                    modifier = Modifier.clickable {
                        viewModel.onMyStatusCategoryChange("출발 전")
                    },
                    text = stringResource(R.string.before_departure),
                    style = WhatNowTheme.typography.headline3.copy(
                        fontSize = 20.sp, color = WhatNowTheme.colors.gray400
                    )
                )

                Text(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .clickable {
                            viewModel.onMyStatusCategoryChange("가는 중")
                        },
                    text = stringResource(R.string.on_the_way),
                    style = WhatNowTheme.typography.headline3.copy(
                        fontSize = 20.sp, color = WhatNowTheme.colors.gray400
                    )
                )

                Text(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .clickable {
                            viewModel.onMyStatusCategoryChange("지각")
                        },
                    text = stringResource(R.string.tardy),
                    style = WhatNowTheme.typography.headline3.copy(
                        fontSize = 20.sp, color = WhatNowTheme.colors.gray400
                    )
                )

                Text(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .clickable {
                            viewModel.onMyStatusCategoryChange("예정시간")
                        },
                    text = stringResource(R.string.before_departure),
                    style = WhatNowTheme.typography.headline3.copy(
                        fontSize = 20.sp, color = WhatNowTheme.colors.gray400
                    )
                )

            }
        }
    }
}