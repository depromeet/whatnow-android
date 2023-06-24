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
import androidx.navigation.NavDestination
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.main.Destination
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onNavigate: (Destination) -> Unit,
    @DrawableRes actionIconRes: Int = R.drawable.alarm,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemsHorizontalPadding =
        ((screenWidth - bottomActionButtonSize) / 2 - bottomBarItemIconSize) / 2 - 4.dp

    @Composable
    fun History() = WhatNowBottomBarItem(
        onSelected = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(end = 22.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = WhatNowTheme.colors.gray100,
                    modifier = Modifier.size(bottomBarItemIconSize)
                ) {}
                Image(
                    painter = painterResource(id = R.drawable.history),
                    contentDescription = null,
                    modifier = Modifier
                        .size(bottomBarItemIconSize)
                )
            }
        },
        onClick = { onNavigate(Destination.History) }
    )

    @Composable
    fun Alarm() = WhatNowBottomBarItem(
        onSelected = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(end = 22.dp)

            ) {
                Surface(
                    shape = CircleShape,
                    color = WhatNowTheme.colors.gray100,
                    modifier = Modifier.size(bottomBarItemIconSize)
                ) {}
                Image(
                    painter = painterResource(id = R.drawable.alarm),
                    contentDescription = null,
                    modifier = Modifier
                        .size(bottomBarItemIconSize)
                )
            }
        },
        onClick = { onNavigate(Destination.Alarm) }
    )

    @Composable
    fun Setting() = WhatNowBottomBarItem(
        onSelected = {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = CircleShape,
                    color = WhatNowTheme.colors.gray100,
                    modifier = Modifier.size(bottomBarItemIconSize)
                ) {}
                Image(
                    painter = painterResource(id = R.drawable.setting),
                    contentDescription = null,
                    modifier = Modifier
                        .size(bottomBarItemIconSize)
                )
            }
        },
        onClick = { onNavigate(Destination.Setting) }
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(bottomPanelHeight)
                .fillMaxWidth(),
            color = WhatNowTheme.colors.gray100,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(screenWidth / 2)
                        .padding(start = 18.dp)
                ) {
                    History()
                    Alarm()
                    Setting()
                }

                // 약속 추가 버튼
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 37.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)

                ) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(screenWidth / 2)
                            .height(56.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = {
                                    onNavigate(Destination.PromiseAdd)
                                }
                            ),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(
                            width = 1.dp,
                            color = WhatNowTheme.colors.gray100
                        ),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.background(Color.Black)
                        ) {
                            Row() {
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
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFFFFFFFF)
                                )
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
        Log.d("ttt", "몇번 눌림")
        onSelected()
    }
}

val bottomPanelHeight = 80.dp
val bottomActionButtonSize = 72.dp
val bottomBarItemIconSize = 20.dp
