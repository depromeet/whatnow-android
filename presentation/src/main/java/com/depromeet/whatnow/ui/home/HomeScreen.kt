package com.depromeet.whatnow.ui.home

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.depromeet.whatnow.component.WhatNowHomeAppBar
import com.depromeet.whatnow.component.WhatNowInactivityMap
import com.depromeet.whatnow.component.WhatNowPromiseList
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhatNowTheme.colors.gray50)
    ) {
        WhatNowHomeAppBar()
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
//                .verticalScroll(rememberScrollState())
        ) {
            WhatNowInactivityMap(modifier = Modifier)
            Text(
                modifier = Modifier.padding(top = 41.dp, bottom = 17.dp),
                text = "예정된 약속",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
//                            style = WhatNowTheme.typography.medium.copy(
//                                fontSize = 16.sp,
//                                color = WhatNowTheme.colors.gray100
//                            )
            )
            WhatNowPromiseList(
                modifier = Modifier,
                promises = listOf("test", "test", "test", "test")
            )
        }
    }
}