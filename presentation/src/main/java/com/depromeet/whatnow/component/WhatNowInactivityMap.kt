package com.depromeet.whatnow.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    modifier: Modifier = Modifier,
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
                topEnd = 28.dp,
                topStart = 28.dp,
                bottomEnd = 28.dp,
                bottomStart = 28.dp
            ),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 41.dp, top = 64.dp)
                .background(WhatNowTheme.colors.gray50)
                .border(
                    BorderStroke(width = 1.dp, color = WhatNowTheme.colors.gray400),
                    shape = RoundedCornerShape(28.dp)
                )

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                    .background(Color.White),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.inactivity_map),
                    contentDescription = "contentDescription",
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = modifier.align(Alignment.BottomCenter)

                ) {
                    Image(
                        modifier = Modifier
                            .padding(start = 53.dp),
                        painter = painterResource(id = R.drawable.inactivity_map_whatnow_icon),
                        contentDescription = "contentDescription",
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier
                            .background(WhatNowTheme.colors.gray400.copy(alpha = 0.8f))
                            .fillMaxWidth()
                            .padding(top = 26.5.dp, bottom = 26.5.dp, start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(R.string.inactivity_map_title),
                            fontSize = 18.sp,
                            color = WhatNowTheme.colors.whatNowBlack,
                            fontWeight = FontWeight.Bold,

                            //                                    style = WhatNowTheme.typography.regular.copy(
                            //                                        fontSize = 14.sp,
                            //                                        color = WhatNowTheme.colors.grey7F
                            //                                    ),
                            textAlign = TextAlign.Center
                        )


                        Surface(
                            modifier = Modifier
                                .width(100.dp)
                                .height(40.dp),
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(
                                width = 0.dp,
                                color = WhatNowTheme.colors.gray500
                            ),
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.background(WhatNowTheme.colors.gray500)
                            ) {
                                Text(
                                    text = stringResource(R.string.promise_create),
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    //                                    style = WhatNowTheme.typography.regular.copy(
                                    //                                        fontSize = 14.sp,
                                    //                                        color = WhatNowTheme.colors.grey7F
                                    //                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }

                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun WhatNowInactivityMapPreview() {
    WhatNowTheme {
        WhatNowInactivityMap(Modifier)
    }
}

