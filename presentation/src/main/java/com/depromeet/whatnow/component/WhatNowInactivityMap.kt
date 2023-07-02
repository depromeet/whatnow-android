package com.depromeet.whatnow.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme


@Composable
fun WhatNowInactivityMap(
    modifier: Modifier = Modifier, isPromise: Boolean
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.whatnow_home_ing_icon),
            contentDescription = null,
            modifier = modifier
                .padding(top = 4.dp, end = 16.dp)
                .align(Alignment.TopEnd)
        )

        Card(
            shape = RoundedCornerShape(
                22.dp
            ),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 64.dp)
                .background(WhatNowTheme.colors.gray50)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(143.dp)
                    .background(Color(0xff1C1C1C).copy(0.8f)),
            ) {

                Image(
                    modifier = modifier.background(Color(0xff1C1C1C).copy(0.8f)),
                    painter = painterResource(id = R.drawable.inactivity_map),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(143.dp)
                        .background(Color(0xff1C1C1C).copy(0.8f))
                ) {}

                Image(
                    modifier = modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 10.dp),
                    painter = painterResource(id = R.drawable.home_promise_empty_icon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Text(
                    modifier = modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 18.dp),
                    text = if (isPromise) stringResource(R.string.inactivity_map_title)
                    else stringResource(R.string.inactivity_not_promise_map_title),
                    style = WhatNowTheme.typography.body2.copy(
                        fontSize = 16.sp,
                        color = WhatNowTheme.colors.gray400
                    ),
                    textAlign = TextAlign.Center
                )

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun WhatNowInactivityMapPreview() {
    WhatNowTheme {
        WhatNowInactivityMap(Modifier, true)
    }
}

