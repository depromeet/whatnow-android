package com.depromeet.whatnow.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.domain.model.GetPromisesUsersStatus
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import java.text.SimpleDateFormat

@Composable
fun WhatNowPromise(modifier: Modifier, promisesUsersStatusItem: GetPromisesUsersStatus) {
    Card(
        shape = RoundedCornerShape(
            topEnd = 16.dp,
            topStart = 16.dp,
            bottomEnd = 16.dp,
            bottomStart = 16.dp
        ),
        modifier = Modifier
            .padding(bottom = 8.dp)

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
                        var date = ""

                        val currentTime: Long = System.currentTimeMillis() // ms로 반환
                        val currentMonth = SimpleDateFormat("MM")
                        val currentDay = SimpleDateFormat("dd")

                        val promiseMonth = promisesUsersStatusItem.endTime.substring(5, 7)
                        val promiseDay = promisesUsersStatusItem.endTime.substring(8, 10)

                        if (currentMonth.format(currentTime) == promiseMonth) {
                            if (currentDay.format(currentTime).toInt() >= promiseDay.toInt() + 2) {
                                date = "D-${
                                    promiseDay.toInt() - currentDay.format(currentTime).toInt()
                                }"
                            }
                        } else {
                            date = "${promiseMonth.toInt()}/$promiseDay"
                        }


                        Text(
                            text = date,
                            style = WhatNowTheme.typography.body2.copy(
                                fontSize = 16.sp, color = WhatNowTheme.colors.whatNowPurple
                            )
                        )
                    }
                }

                Column(
                    modifier = modifier.padding(start = 16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = promisesUsersStatusItem.title,
                        style = WhatNowTheme.typography.body1.copy(
                            fontSize = 18.sp, color = WhatNowTheme.colors.whatNowBlack
                        )
                    )
                    Row(
                        modifier = modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = "서울시 종로구",
                            style = WhatNowTheme.typography.caption2.copy(
                                fontSize = 14.sp, color = WhatNowTheme.colors.gray700
                            )
                        )
                    }
                }
            }
        }
    }
}