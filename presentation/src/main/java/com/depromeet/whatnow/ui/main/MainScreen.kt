package com.depromeet.whatnow.ui.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.depromeet.whatnow.component.NoRippleInteractionSource
import com.depromeet.whatnow.component.WhatNowBottomBar
import com.depromeet.whatnow.component.WhatNowBottomSheetContent
import com.depromeet.whatnow.component.bottomPanelHeight
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.history.HistoryScreen
import com.depromeet.whatnow.ui.home.HomeScreen
import com.depromeet.whatnow.ui.promiseAdd.PromiseAddActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import kotlinx.coroutines.launch

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
                onNavigate = { navigator.navigateTo(it) },
                isPromise = uiState.promisesUsersStatus.isNotEmpty()
            )
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = Destination.Home.route,
            modifier = Modifier.padding(bottom = bottomPanelHeight)
        ) {
            composable(Destination.Home.route) {
                HomeScreen()
            }
            composable(Destination.History.route) {
                startHistoryActivity()
            }
            composable(Destination.Alarm.route) {
                startAlarmActivity()
            }
            composable(Destination.Setting.route) {
                startSettingActivity()
            }
            composable(Destination.PromiseAdd.route) {
                startPromiseAddActivity()
            }
        }
    }

}
