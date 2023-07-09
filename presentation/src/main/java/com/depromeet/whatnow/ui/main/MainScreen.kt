package com.depromeet.whatnow.ui.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.depromeet.whatnow.component.WhatNowBottomBar
import com.depromeet.whatnow.ui.home.HomeScreen
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    startHistoryActivity: () -> Unit,
    startAlarmActivity: () -> Unit,
    startSettingActivity: () -> Unit,
    startPromiseAddActivity: () -> Unit
) {

    val navController = rememberNavController()
    val navigator = rememberNavigator(navController = navController)
    val uiState by viewModel.uiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    BackHandler(enabled = modalBottomSheetState.isVisible) {
        coroutineScope.launch { modalBottomSheetState.hide() }
    }

    Scaffold(
        bottomBar = {
            WhatNowBottomBar(
                startHistoryActivity = startHistoryActivity,
                startAlarmActivity = startAlarmActivity,
                startSettingActivity = startSettingActivity,
                startPromiseAddActivity = startPromiseAddActivity,
                isPromise = uiState.promisesUsersStatus.isNotEmpty()
            )
        },
    ) { innerPadding ->
        HomeScreen(modifier = Modifier.padding(innerPadding))
    }
}
