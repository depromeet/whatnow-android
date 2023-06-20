package com.depromeet.whatnow.ui.highlight

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.component.WhatNowHighlightTile
import com.depromeet.whatnow.component.WhatNowSimpleTopBar
import com.depromeet.whatnow.ui.R

@Composable
fun HighlightScreen(
    viewModel: HighlightViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            WhatNowSimpleTopBar(onBack = onBack, titleRes = R.string.highlight)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
        ) {
            items(uiState.highlights.size) {
                WhatNowHighlightTile(
                    highlight = uiState.highlights[it],
                    isStart = it == 0,
                    isEnd = it == uiState.highlights.lastIndex
                )
            }
        }
    }
}
