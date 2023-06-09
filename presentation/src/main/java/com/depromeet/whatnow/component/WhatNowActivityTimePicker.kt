package com.depromeet.whatnow.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowTimePickerPicker(
    modifier: Modifier,
    width: Dp,
    height: Dp,
    roundedCornerShape: RoundedCornerShape,
    hour: String,
    min: String,
    style: TextStyle
) {
    Row() {
        Card(

            modifier = modifier
                .border(
                    BorderStroke(width = 0.dp, color = WhatNowTheme.colors.whatNowPurple),
                    shape = roundedCornerShape
                ),
            shape = roundedCornerShape,
        ) {
            Box(
                modifier = Modifier
                    .width(width)
                    .height(height)
                    .background(WhatNowTheme.colors.whatNowPurple),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = hour,
                    style = style
                )
            }
        }

        Column(
            modifier = modifier
                .height(height)
                .padding(start = 4.dp, end = 4.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            val color = WhatNowTheme.colors.whatNowPurple

            Canvas(
                modifier = Modifier
                    .padding(bottom = 3.dp)
                    .width(4.dp)
                    .height(4.dp)
            ) {
                drawCircle(
                    color = color
                )
            }

            Canvas(
                modifier = Modifier
                    .padding(top = 3.dp)
                    .width(4.dp)
                    .height(4.dp)
            ) {
                drawCircle(
                    color = color
                )
            }

        }

        Card(
            shape = RoundedCornerShape(
                topEnd = 16.dp,
                topStart = 16.dp,
                bottomEnd = 16.dp,
                bottomStart = 16.dp
            ),
            modifier = Modifier
                .border(
                    BorderStroke(width = 0.dp, color = WhatNowTheme.colors.whatNowPurple),
                    shape = RoundedCornerShape(16.dp)
                )


        ) {
            Box(
                modifier = Modifier
                    .width(width)
                    .height(height)
                    .background(WhatNowTheme.colors.whatNowPurple),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = min,
                    style = style
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun WhatNowTimePickerPreview() {
    WhatNowTheme {
        WhatNowTimePickerPicker(
            Modifier,
            56.dp,
            56.dp,
            RoundedCornerShape(16.dp),
            "58",
            "33",
            WhatNowTheme.typography.headline3.copy(
                fontSize = 20.sp,
                color = Color.White
            )
        )
    }
}
