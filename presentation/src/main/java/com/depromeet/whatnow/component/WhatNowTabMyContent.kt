package com.depromeet.whatnow.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.promiseActivate.PromiseActivateViewModel
import com.depromeet.whatnow.ui.promiseActivate.PromiseEmojiTab
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowTabMyContent(
    viewModel: PromiseActivateViewModel,
    modifier: Modifier,
    promises: List<Promise>
) {

    val uiState by viewModel.uiState.collectAsState()
    val isClickedMyStatus by viewModel.isClickedMyStatus.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                WhatNowProfile(
                    promise = promises[0],
                    profileImageSize = 56.dp,
                    statusImageSize = 88.dp
                )
                Column(modifier = modifier.padding(start = 22.dp)) {
                    Text(
                        text = promises[0].participants[0].name,
                        style = WhatNowTheme.typography.body1.copy(
                            fontSize = 18.sp, color = WhatNowTheme.colors.gray50
                        )
                    )
                    Row(
                        modifier = modifier.padding(top = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.state_icon),
                            contentDescription = null,
                        )
                        Text(
                            text = "씻는중",
                            modifier = modifier.padding(start = 3.67.dp),
                            style = WhatNowTheme.typography.caption2.copy(
                                fontSize = 14.sp, color = WhatNowTheme.colors.gray400
                            )
                        )
                    }

                }
            }
            Surface(
                modifier = Modifier
                    .width(84.dp)
                    .height(40.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    viewModel.onClickedMyStatus()
                    Log.d("ttt", isClickedMyStatus.toString())
                }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.background(WhatNowTheme.colors.whatNowPurple)
                ) {
                    Text(
                        text = stringResource(R.string.state_change),
                        style = WhatNowTheme.typography.body3.copy(
                            fontSize = 14.sp,
                            color = Color.White,
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        WhatNowEmojiTab(
            selected = uiState.selectedEmojiTab,
            onMusicEmojiClicked = { viewModel.selectEmojiTab(PromiseEmojiTab.Music) },
            onPoopEmojiClicked = { viewModel.selectEmojiTab(PromiseEmojiTab.Poop) },
            onHeartEmojiClicked = { viewModel.selectEmojiTab(PromiseEmojiTab.Heart) },
            onFootprintEmojiClicked = { viewModel.selectEmojiTab(PromiseEmojiTab.FootPrint) }
        )

        when (uiState.selectedEmojiTab) {
            PromiseEmojiTab.Music -> {
                WhatNowTabMyContentList(modifier = modifier, promises = uiState.musicEmoji)
            }

            PromiseEmojiTab.Poop -> {
                WhatNowTabMyContentList(modifier = modifier, promises = uiState.poopEmoji)
            }

            PromiseEmojiTab.Heart -> {
                WhatNowTabMyContentList(modifier = modifier, promises = uiState.heartEmoji)
            }

            PromiseEmojiTab.FootPrint -> {
                WhatNowTabMyContentList(modifier = modifier, promises = uiState.footPrintEmoji)
            }
        }
    }
}