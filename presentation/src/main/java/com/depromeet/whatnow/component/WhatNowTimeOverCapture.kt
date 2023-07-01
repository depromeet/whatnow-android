package com.depromeet.whatnow.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowTimeOverCapture(
    modifier: Modifier = Modifier,
    location: String,
    imageUrl: String
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.timeover_capture_time),
                style = WhatNowTheme.typography.headline3,
                color = WhatNowTheme.colors.whatNowBlack
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = WhatNowTheme.colors.gray700
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = location,
                    style = WhatNowTheme.typography.caption2,
                    color = WhatNowTheme.colors.gray700
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            ElevatedCard(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(328 / 220f)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(328 / 220f)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.img_bomb),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp)
                .align(Alignment.TopEnd)
        )
        Image(
            painter = painterResource(id = R.drawable.img_capture),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .padding(start = 16.dp)
                .width(120.dp)
                .align(Alignment.BottomStart)
        )
    }
}
