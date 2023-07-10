package com.depromeet.whatnow.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.domain.model.Users
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.model.User
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowTabOtherContentCard(
    modifier: Modifier, users: Users
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff35373E),
        ), shape = RoundedCornerShape(16.dp), modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(316 / 104f)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            WhatNowProfile(users = users, profileImageSize = 56.dp, statusImageSize = 88.dp)
            Column(modifier = modifier.padding(start = 20.dp)) {
                Text(
                    text = users.nickname,
                    style = WhatNowTheme.typography.body2.copy(
                        fontSize = 16.sp, color = WhatNowTheme.colors.gray50
                    )
                )
                Text(
                    text = stringResource(R.string.new_title),
                    modifier = modifier.padding(top = 4.dp),
                    style = WhatNowTheme.typography.caption2.copy(
                        fontSize = 14.sp, color = WhatNowTheme.colors.whatNowYellow
                    )
                )
                Row(
                    modifier = modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .width(80.dp)
                            .height(28.dp),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.background(WhatNowTheme.colors.gray600)
                        ) {
                            Text(
                                text = stringResource(R.string.state),
                                style = WhatNowTheme.typography.caption2.copy(
                                    fontSize = 12.sp,
                                    color = WhatNowTheme.colors.gray200,
                                ),
                                textAlign = TextAlign.Center
                            )
                        }

                    }

                    Icon(
                        modifier = modifier.padding(start = 9.dp),
                        painter = painterResource(id = R.drawable.arrow_forward),
                        contentDescription = null,
                    )

                    Surface(
                        modifier = Modifier
                            .padding(start = 9.dp)
                            .width(80.dp)
                            .height(28.dp),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.background(WhatNowTheme.colors.whatNowPurple)
                        ) {
                            Text(
                                text = stringResource(R.string.state),
                                style = WhatNowTheme.typography.caption3.copy(
                                    fontSize = 12.sp,
                                    color = WhatNowTheme.colors.gray200,
                                ),
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }

            }
        }
    }
}