package com.depromeet.whatnow.ui.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.archive.ArchiveTab
import com.depromeet.whatnow.ui.archive.ArchiveViewModel
import com.depromeet.whatnow.ui.archive.FuturePromiseContent
import com.depromeet.whatnow.ui.archive.PastPromiseContent
import com.depromeet.whatnow.ui.promiseActivate.PromiseActivateTab
import com.depromeet.whatnow.ui.promiseActivate.PromiseActivateViewModel
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WhatNowBottomSheetScaffold(
    viewModel: PromiseActivateViewModel,
    modifier: Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val contextForToast = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 300.dp,
        sheetBackgroundColor = WhatNowTheme.colors.whatNowBlack,
        sheetShape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        sheetContent = {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {
                WhatNowBottomSheetContent(modifier = modifier)

                WhatNowTab(
                    selected = uiState.selectedTab,
                    onTotalClicked = { viewModel.selectTab(PromiseActivateTab.All) },
                    onMeClicked = { viewModel.selectTab(PromiseActivateTab.My) },
                    onFriendClicked = { viewModel.selectTab(PromiseActivateTab.Other) },

                    )

                when (uiState.selectedTab) {
                    PromiseActivateTab.All -> {
                        WhatNowTabAllContent(
                            modifier = modifier,
                            promises = uiState.allProfile,
                            onCreate = {})
                    }

                    PromiseActivateTab.My -> {
                        WhatNowTabMyContent(
                            modifier = Modifier,
                            promises = uiState.myProfile,
                            viewModel = viewModel
                        )
                    }

                    PromiseActivateTab.Other -> {
                        WhatNowTabOtherContent(promises = uiState.otherProfile, onCreate = {})
                    }
                }
            }
        }) {
        // app UI
        WhatNowNaverMap(modifier = Modifier)

    }

}