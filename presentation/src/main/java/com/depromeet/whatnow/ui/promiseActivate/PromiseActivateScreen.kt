package com.depromeet.whatnow.ui.promiseActivate

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.depromeet.whatnow.component.WhatNowBottomSheetScaffold


@Composable
fun PromiseActivateScreen(viewModel: PromiseActivateViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val isRefresh by viewModel.isRefresh.collectAsState()
    val launcher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.refresh()
        }
    }
    WhatNowBottomSheetScaffold(modifier = Modifier, viewModel = viewModel)
}