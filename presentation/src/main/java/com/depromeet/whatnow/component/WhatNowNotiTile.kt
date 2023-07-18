package com.depromeet.whatnow.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.depromeet.whatnow.ui.utils.DateDisplayUtil.toRelativeDateString
import java.time.LocalDateTime

@Composable
fun WhatNowNotiTile(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    footer: (@Composable () -> Unit)? = null,
    title: String,
    subtitle: String,
    dateTime: LocalDateTime
) {
    val displayedTime = dateTime.toRelativeDateString()
    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(NOTI_TILE_HEIGHT)
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        header()
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, style = WhatNowTheme.typography.body2)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = WhatNowTheme.typography.caption3,
                color = WhatNowTheme.colors.gray800
            )
            Text(
                text = displayedTime,
                style = WhatNowTheme.typography.caption3,
                color = if (displayedTime == "NEW") WhatNowTheme.colors.whatNowPurple
                else WhatNowTheme.colors.gray700
            )
        }
        footer?.invoke()
    }
}

private val NOTI_TILE_HEIGHT = 64.dp
