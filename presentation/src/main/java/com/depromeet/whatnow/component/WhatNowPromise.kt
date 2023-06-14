package com.depromeet.whatnow.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowPromise(modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(
            topEnd = 16.dp,
            topStart = 16.dp,
            bottomEnd = 16.dp,
            bottomStart = 16.dp
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)

    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = 23.dp)

            ) {
                Card(
                    shape = RoundedCornerShape(
                        topEnd = 18.dp,
                        topStart = 18.dp,
                        bottomEnd = 18.dp,
                        bottomStart = 18.dp
                    ),
                    modifier = Modifier
                        .border(
                            BorderStroke(width = 0.dp, color = WhatNowTheme.colors.gray100),
                            shape = RoundedCornerShape(18.dp)
                        )


                ) {
                    Box(
                        modifier = Modifier
                            .width(72.dp)
                            .height(48.dp)
                            .background(WhatNowTheme.colors.gray100),
                        contentAlignment = Alignment.Center

                    ) {
                        Text(
                            text = "D-2",
                            color = WhatNowTheme.colors.whatNowPurple,
                            fontSize = 16.sp
//                            style = WhatNowTheme.typography.medium.copy(
//                                fontSize = 16.sp,
//                                color = WhatNowTheme.colors.gray100
//                            )
                        )
                    }
                }
                Column() {
                    Row() {

                        Spacer(modifier = Modifier.width(16.dp))
                        Column(
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "먹쨩이 되고싶은 모임",
                                color = Color.Black,
                                fontSize = 18.sp
//                            style = WhatNowTheme.typography.medium.copy(
//                                fontSize = 16.sp,
//                                color = WhatNowTheme.colors.gray100
//                            )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row() {
                                Icon(
                                    painter = painterResource(id = R.drawable.location),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "서울시 종로구",
                                    color = Color.Black,
                                    fontSize = 12.sp

//                            style = WhatNowTheme.typography.regular.copy(
//                                fontSize = 16.sp,
//                                color = Color(0xFF818284)
//                            )
                                )
                            }

                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)

                        ) {
                            Surface(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .width(24.dp)
                                    .height(24.dp)
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null,
                                        onClick = { }
                                    )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.chevron_right),
                                    contentDescription = null,
                                )
                            }
                        }
                    }


                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WhatNowPromisePreview() {
    WhatNowTheme {
        WhatNowPromise(Modifier)
    }
}
