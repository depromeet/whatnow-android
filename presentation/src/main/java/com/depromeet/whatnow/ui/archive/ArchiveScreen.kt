package com.depromeet.whatnow.ui.archive

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.depromeet.whatnow.component.WhatNowSimpleTopBar
import com.depromeet.whatnow.ui.R

@Composable
fun ArchiveScreen(
    viewModel: ArchiveViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            WhatNowSimpleTopBar(
                onBack = onBack,
                titleRes = R.string.promise_archive,
                actionIconRes = R.drawable.ic_calendar
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

        }
    }
}
