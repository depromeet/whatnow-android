package com.depromeet.whatnow.ui.promiseAdd

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.component.KnowllyButtonDefaults
import com.depromeet.whatnow.component.WhatNowButton
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun PromiseDetailScreen(
    viewModel: PromiseAddViewModel,
    goHomeClick: () -> Unit,
    inviteClick: () -> Unit
) {
    val selectedCalendar = viewModel.selectedCalendar.collectAsState()
    val selectedTime = viewModel.selectedTime.collectAsState()
    val selectedPlace = viewModel.selectedPlace.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.fillMaxWidth().aspectRatio(360/40f))

            Image(
                painter = painterResource(id = R.drawable.promise_detail),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(167/180f)

            )
            Column(
                modifier = Modifier
                    .padding(top = 23.8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.promise_detail_title),
                    style = WhatNowTheme.typography.headline2,
                    color = WhatNowTheme.colors.gray900,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.promise_detail_msg),
                    modifier = Modifier.padding(top = 8.dp),
                    style = WhatNowTheme.typography.body3,
                    color = WhatNowTheme.colors.gray900,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.fillMaxWidth().aspectRatio(360/44f))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.promise_detail),
                style = WhatNowTheme.typography.body1,
                color = WhatNowTheme.colors.gray900,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.fillMaxWidth().aspectRatio(360/16f))
            makeBox(R.drawable.ic_calendar, R.string.promise_calendar, selectedCalendar.value)
            Spacer(modifier = Modifier.fillMaxWidth().aspectRatio(360/24f))
            makeBox(R.drawable.clock2, R.string.promise_time, selectedTime.value)
            Spacer(modifier = Modifier.fillMaxWidth().aspectRatio(360/24f))
            makeBox(R.drawable.location, R.string.promise_place, selectedPlace.value)
            Spacer(modifier = Modifier.fillMaxWidth().aspectRatio(360/37f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.End) {
                    Image(
                        painter = painterResource(id = R.drawable.tesd),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth(0.66f)
                            .aspectRatio(250/48f)
                    )
                    Spacer(modifier = Modifier.fillMaxWidth().aspectRatio(360/4f))
                    Row {
                        WhatNowButton(
                            buttonColors = KnowllyButtonDefaults.textPurpleButtonColors,
                            text = stringResource(id = R.string.promise_detail_btn_home),
                            onClick = { goHomeClick() },
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(56.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        WhatNowButton(
                            buttonColors = KnowllyButtonDefaults.textGrayButtonColors,
                            text = stringResource(id = R.string.promise_detail_btn_invite),
                            onClick = { inviteClick() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun makeBox(paintResource: Int, titleResource: Int, data: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().aspectRatio(360/24f)
    ) {
        Icon(
            painter = painterResource(id = paintResource),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(4.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = titleResource),
                    style = WhatNowTheme.typography.caption1,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = data,
                    style = WhatNowTheme.typography.caption1,
                    color = WhatNowTheme.colors.gray900,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
fun PromiseDetailScreenPreview() {
    WhatNowTheme() {
        Surface() {

        }
    }
}