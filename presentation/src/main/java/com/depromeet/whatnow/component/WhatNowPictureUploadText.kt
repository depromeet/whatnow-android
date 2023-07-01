package com.depromeet.whatnow.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.picture.PictureUploadText
import com.depromeet.whatnow.ui.theme.WhatNowTheme


@Composable
fun WhatNowPictureUploadText(
    item: PictureUploadText, modifier: Modifier, onClick: () -> Unit
) {

    Surface(
        onClick = onClick,
        modifier = Modifier
            .height(40.dp)
            .padding(end = 6.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 2.dp,
            color = if (item.enabled) WhatNowTheme.colors.whatNowPurple else Color.White
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = if (item.enabled) modifier.background(Color(0xFFABADFF)) else modifier.background(
                Color.White
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = item.img),
                    contentDescription = null,
                )

                Text(
                    modifier = modifier.padding(start = 8.dp),
                    text = item.text,
                    style = WhatNowTheme.typography.body3.copy(
                        fontSize = 14.sp,
                        color = WhatNowTheme.colors.whatNowBlack,
                    ),
                    textAlign = TextAlign.Center,
                )
            }

        }
    }

}
