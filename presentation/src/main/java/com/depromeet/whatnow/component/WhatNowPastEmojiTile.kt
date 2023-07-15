package com.depromeet.whatnow.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.ArrivalState
import com.depromeet.whatnow.ui.model.User
import com.depromeet.whatnow.ui.theme.Roboto
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowPastEmojiTile(
    modifier: Modifier = Modifier,
    user: User
) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.profileImageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = user.name,
                style = WhatNowTheme.typography.body2,
                color = WhatNowTheme.colors.whatNowBlack
            )
            Spacer(modifier = Modifier.weight(1f))
            when (user.arrivalState) {
                ArrivalState.Ready -> {
                    Text(
                        text = stringResource(id = R.string.arrived),
                        style = WhatNowTheme.typography.body3,
                        color = WhatNowTheme.colors.whatNowPurple
                    )
                }

                ArrivalState.Late -> {
                    Text(
                        text = stringResource(id = R.string.late),
                        style = WhatNowTheme.typography.body3,
                        color = WhatNowTheme.colors.whatNowError
                    )
                }

                else -> {}
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            border = BorderStroke(width = 1.dp, color = WhatNowTheme.colors.gray200),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .height(32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                user.emojis.toList().sortedByDescending { it.second }.forEachIndexed { idx, pair ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.width(64.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = pair.first.iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = if (pair.second > 0) {
                                WhatNowTheme.colors.whatNowPurple
                            } else {
                                WhatNowTheme.colors.gray400
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = pair.second.toString(),
                            style = TextStyle(
                                fontFamily = Roboto,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = if (pair.second > 0) {
                                WhatNowTheme.colors.whatNowPurple
                            } else {
                                WhatNowTheme.colors.gray400
                            },
                            textAlign = TextAlign.Center
                        )
                    }
                    if (idx != user.emojis.size - 1) {
                        Surface(
                            modifier = Modifier
                                .height(16.dp)
                                .width(2.dp),
                            color = WhatNowTheme.colors.gray200
                        ) {}
                    }
                }
            }
        }
    }
}
