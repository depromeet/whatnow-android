package com.depromeet.whatnow.ui.archive.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.component.WhatNowHighlightTile
import com.depromeet.whatnow.component.WhatNowIconButton
import com.depromeet.whatnow.component.WhatNowImageCarousel
import com.depromeet.whatnow.component.WhatNowPastEmojiTile
import com.depromeet.whatnow.component.WhatNowSimpleTopBar
import com.depromeet.whatnow.component.WhatNowTimeOverCapture
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import java.time.format.DateTimeFormatter

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val scrollState = rememberScrollState()
    val promise = uiState.selectedPromise

    Scaffold(
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WhatNowSimpleTopBar(
                    onBack = onBack,
                    titleRes = R.string.past_promise,
                    actionIconRes = R.drawable.ic_calendar,
                    onAction = {}
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    WhatNowIconButton(
                        iconRes = R.drawable.ic_chevron_left,
                        tint = WhatNowTheme.colors.whatNowBlack,
                        size = 24.dp,
                        onClick = {}
                    )
                    Text(
                        text = promise.datetime.format(
                            DateTimeFormatter.ofPattern(stringResource(id = R.string.month_day_fmt))
                        ),
                        style = WhatNowTheme.typography.body1,
                        color = WhatNowTheme.colors.whatNowBlack
                    )
                    WhatNowIconButton(
                        iconRes = R.drawable.ic_chevron_right,
                        tint = WhatNowTheme.colors.whatNowBlack,
                        size = 24.dp,
                        onClick = {}
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
        ) {
            WhatNowImageCarousel(
                imageUrls = promise.imageUrls
            )
            Spacer(modifier = Modifier.height(24.dp))
            WhatNowTimeOverCapture(
                location = promise.location,
                imageUrl = promise.timeOverImageUrl
            )
            Spacer(modifier = Modifier.height(24.dp))
            Divider(thickness = 10.dp, color = WhatNowTheme.colors.gray100)
            Text(
                text = stringResource(id = R.string.people_meet),
                style = WhatNowTheme.typography.headline3,
                color = WhatNowTheme.colors.whatNowBlack,
                modifier = Modifier.padding(16.dp)
            )
            promise.participants.forEach {
                WhatNowPastEmojiTile(
                    modifier = Modifier.padding(bottom = 16.dp),
                    user = it
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(thickness = 10.dp, color = WhatNowTheme.colors.gray100)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.highlight),
                    style = WhatNowTheme.typography.headline3,
                    color = WhatNowTheme.colors.whatNowBlack
                )
                WhatNowIconButton(
                    iconRes = R.drawable.ic_chevron_right,
                    tint = WhatNowTheme.colors.whatNowBlack,
                    size = 24.dp,
                    onClick = {}
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            promise.highlights.take(HIGHLIGHT_DISPLAY_COUNT).forEachIndexed { index, highlight ->
                WhatNowHighlightTile(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    highlight = highlight,
                    isStart = index == 0,
                    isEnd = index == HIGHLIGHT_DISPLAY_COUNT - 1
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

private const val HIGHLIGHT_DISPLAY_COUNT = 3
