package com.depromeet.whatnow.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowArrivalTime(modifier: Modifier, time: String) {
    Surface(
        modifier = modifier
            .width(120.dp)
            .height(40.dp),
        shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp, bottomEnd = 12.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.background(WhatNowTheme.colors.whatNowPurple),
        ) {
            Text(
                text = "%s 도착".format(time),
                style = WhatNowTheme.typography.body2.copy(
                    fontSize = 16.sp, color = Color(0xFFF9F9F9), textAlign = TextAlign.Center
                )
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun WhatNowArrivalTimePreview() {
    WhatNowTheme {
        WhatNowArrivalTime(modifier = Modifier, "16시 31분")
    }
}
