package com.depromeet.whatnow.component

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.main.Destination
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowBottomBar(
    modifier: Modifier = Modifier,
    startHistoryActivity: () -> Unit,
    startAlarmActivity: () -> Unit,
    startSettingActivity: () -> Unit,
    startPromiseAddActivity: () -> Unit,
    isPromise: Boolean
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemsHorizontalPadding =
        ((screenWidth - bottomActionButtonSize) / 2 - bottomBarItemIconSize) / 2 - 4.dp

    @Composable
    fun History() = IconButton(
        modifier = modifier
            .padding(start = 8.dp),
        onClick = { startHistoryActivity() }) {
        Icon(
            modifier = modifier.size(24.dp),
            painter = painterResource(id = R.drawable.history),
            contentDescription = null,
            tint = Color.White
        )
    }


    @Composable
    fun Alarm() = IconButton(
        modifier = modifier
            .padding(start = 16.dp),
        onClick = { startAlarmActivity() }) {
        Icon(
            modifier = modifier.size(24.dp),
            painter = painterResource(id = R.drawable.alarm),
            contentDescription = null,
            tint = Color.White
        )
    }

    @Composable
    fun Setting() = IconButton(
        modifier = modifier
            .padding(start = 16.dp),
        onClick = { startSettingActivity() }) {
        Icon(
            modifier = modifier
                .size(24.dp),
            painter = painterResource(id = R.drawable.setting),
            contentDescription = null,
            tint = Color.White
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(bottomPanelHeight)
                .fillMaxWidth(),
            color = WhatNowTheme.colors.whatNowBlack,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(screenWidth / 2)
                ) {
                    History()
                    Alarm()
                    Setting()
                }

                // 약속 추가 버튼
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, end = 16.dp, bottom = 12.dp)

                ) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .height(56.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = { startPromiseAddActivity() }
                            ),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.background(WhatNowTheme.colors.whatNowPurple)
                        ) {
                            if (!isPromise) {

                                Text(
                                    modifier = modifier.padding(start = 20.dp, end = 20.dp),
                                    text = stringResource(R.string.fest_promise_create),
                                    style = WhatNowTheme.typography.body1.copy(
                                        fontSize = 18.sp, color = Color.White
                                    )
                                )
                            } else {

                                Row(modifier = modifier.padding(start = 15.dp, end = 20.dp)) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.add),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier
                                            .height(14.dp)
                                            .align(Alignment.CenterVertically)
                                            .padding(end = 9.dp)
                                    )
                                    Text(
                                        text = stringResource(R.string.promise_create),
                                        style = WhatNowTheme.typography.body1.copy(
                                            fontSize = 18.sp, color = Color.White
                                        )
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WhatNowBottomBarItem(
    onSelected: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        onSelected()
    }
}

val bottomPanelHeight = 80.dp
val bottomActionButtonSize = 72.dp
val bottomBarItemIconSize = 20.dp
