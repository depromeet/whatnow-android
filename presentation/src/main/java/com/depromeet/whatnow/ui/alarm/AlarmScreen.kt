package com.depromeet.whatnow.ui.alarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.component.WhatNowImageStackVert
import com.depromeet.whatnow.component.WhatNowNotiTile
import com.depromeet.whatnow.component.WhatNowSimpleTopBar
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import java.time.LocalDateTime


@Composable
fun AlarmScreen(
    viewModel: AlarmViewModel,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            WhatNowSimpleTopBar(
                onBack = onBack,
                titleRes = R.string.notification
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.today_promise),
                    style = WhatNowTheme.typography.headline3,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            item {
                WhatNowNotiTile(
                    header = {
                        Image(
                            painter = painterResource(id = R.drawable.img_clock),
                            contentDescription = null,
                            modifier = Modifier.width(48.dp)
                        )
                    },
                    title = "위치 공유 종료!",
                    subtitle = "공유 종료! 즐거운 약속의 시작!",
                    dateTime = LocalDateTime.now()
                )
            }
            item {
                WhatNowNotiTile(
                    header = {
                        WhatNowImageStackVert(
                            imageUrls = listOf("", ""),
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                    },
                    footer = {
                        Image(
                            painter = painterResource(id = R.drawable.img_noti_01),
                            contentDescription = null,
                            modifier = Modifier.height(56.dp)
                        )
                    },
                    title = "김동호랑 붙어라!",
                    subtitle = "친구가 근처에 있어!",
                    dateTime = LocalDateTime.now().minusMinutes(10)
                )
            }
            item {
                WhatNowNotiTile(
                    header = {
                        Image(
                            painter = painterResource(id = R.drawable.img_timeover_bomb),
                            contentDescription = null,
                            modifier = Modifier.width(48.dp)
                        )
                    },
                    title = "TIMEOVER!",
                    subtitle = "친구들에게 용서를 비는 사진을 보내봐!",
                    dateTime = LocalDateTime.now().minusMinutes(48)
                )
            }
            item {
                WhatNowNotiTile(
                    header = {
                        WhatNowImageStackVert(
                            imageUrls = List(4) { "" },
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                    },
                    footer = {
                        Image(
                            painter = painterResource(id = R.drawable.img_noti_03),
                            contentDescription = null,
                            modifier = Modifier.height(56.dp)
                        )
                    },
                    title = "똥 이모지 100개 달성!",
                    subtitle = "똥 이모지 100개 달성!",
                    dateTime = LocalDateTime.now().minusMinutes(10)
                )
            }
            item {
                WhatNowNotiTile(
                    header = {
                        Image(
                            painter = painterResource(id = R.drawable.img_selfie),
                            contentDescription = null,
                            modifier = Modifier.width(48.dp)
                        )
                    },
                    title = "지각한 친구의 사진 도착",
                    subtitle = "지각한 친구가 보낸 사진을 확인해봐!",
                    dateTime = LocalDateTime.now().minusMinutes(48)
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(
                    text = stringResource(id = R.string.previous_promise),
                    style = WhatNowTheme.typography.headline3,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            item {
                WhatNowNotiTile(
                    header = {
                        Image(
                            painter = painterResource(id = R.drawable.img_clock),
                            contentDescription = null,
                            modifier = Modifier.width(48.dp)
                        )
                    },
                    title = "위치 공유 종료!",
                    subtitle = "공유 종료! 즐거운 약속의 시작!",
                    dateTime = LocalDateTime.now()
                )
            }
            item {
                WhatNowNotiTile(
                    header = {
                        WhatNowImageStackVert(
                            imageUrls = listOf("", ""),
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                    },
                    footer = {
                        Image(
                            painter = painterResource(id = R.drawable.img_noti_01),
                            contentDescription = null,
                            modifier = Modifier.height(56.dp)
                        )
                    },
                    title = "김동호랑 붙어라!",
                    subtitle = "친구가 근처에 있어!",
                    dateTime = LocalDateTime.now().minusMinutes(10)
                )
            }
            item {
                WhatNowNotiTile(
                    header = {
                        Image(
                            painter = painterResource(id = R.drawable.img_timeover_bomb),
                            contentDescription = null,
                            modifier = Modifier.width(48.dp)
                        )
                    },
                    title = "TIMEOVER!",
                    subtitle = "친구들에게 용서를 비는 사진을 보내봐!",
                    dateTime = LocalDateTime.now().minusMinutes(48)
                )
            }
            item {
                WhatNowNotiTile(
                    header = {
                        WhatNowImageStackVert(
                            imageUrls = List(4) { "" },
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                    },
                    footer = {
                        Image(
                            painter = painterResource(id = R.drawable.img_noti_03),
                            contentDescription = null,
                            modifier = Modifier.height(56.dp)
                        )
                    },
                    title = "똥 이모지 100개 달성!",
                    subtitle = "똥 이모지 100개 달성!",
                    dateTime = LocalDateTime.now().minusMinutes(10)
                )
            }
            item {
                WhatNowNotiTile(
                    header = {
                        Image(
                            painter = painterResource(id = R.drawable.img_selfie),
                            contentDescription = null,
                            modifier = Modifier.width(48.dp)
                        )
                    },
                    title = "지각한 친구의 사진 도착",
                    subtitle = "지각한 친구가 보낸 사진을 확인해봐!",
                    dateTime = LocalDateTime.now().minusMinutes(48)
                )
            }
        }
    }
}
