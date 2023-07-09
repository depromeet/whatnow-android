package com.depromeet.whatnow.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.depromeet.whatnow.domain.model.InteractionsDetail
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowTabMyContentList(
    modifier: Modifier,
    interactionsDetail: List<InteractionsDetail>,
) {
    Column() {
        interactionsDetail.map {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "+${it.count}", style = WhatNowTheme.typography.body1.copy(
                        fontSize = 18.sp, color = Color.White
                    )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        modifier = modifier
                            .padding(end = 8.dp)
                            .height(21.dp),
                        text = it.senderUser.nickname,
                        style = WhatNowTheme.typography.caption2.copy(
                            fontSize = 14.sp, color = Color.White
                        )
                    )
                    AsyncImage(
                        model = it.senderUser.profileImg,
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .border(
                                width = (1.5).dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(14.dp)
                            )
                    )
                }

            }
        }
    }
}