package com.depromeet.whatnow.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowTabAllContent(
    modifier: Modifier,
    promises: List<Promise>,
    onCreate: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.arrived_other),
                    style = WhatNowTheme.typography.headline3.copy(
                        fontSize = 20.sp, color = WhatNowTheme.colors.gray50
                    )
                )

                Surface(
                    modifier = Modifier
                        .width(116.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = WhatNowTheme.colors.whatNowPurple.copy(alpha = 0.1f)
                    ),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.background(WhatNowTheme.colors.whatNowPurple)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.camera),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(end = 5.33.dp)
                            )

                            Text(
                                text = stringResource(R.string.send_photo),
                                style = WhatNowTheme.typography.body3.copy(
                                    fontSize = 14.sp,
                                    color = Color.White,
                                ),
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
            }
            Spacer(modifier = modifier.padding(bottom = 24.dp ))
        }
        items(promises) {
            WhatNowTabAllContentGrid(promise = it, modifier = modifier)
        }

    }
}