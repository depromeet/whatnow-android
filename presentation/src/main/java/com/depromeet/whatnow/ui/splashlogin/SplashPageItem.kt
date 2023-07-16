package com.depromeet.whatnow.ui.splashlogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.theme.WhatNowTheme


@Composable
fun SplashPageItem(
    pageItem: PageItem,
    modifier: Modifier = Modifier,
    test: Boolean,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState()),horizontalAlignment = Alignment.CenterHorizontally) {
            if (test) {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(360 / 35f))
            } else {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(360 / 74f))
            }
            Image(
                painter = painterResource(id = pageItem.image),
                contentDescription = null,
                modifier = if (test) {
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(360 / 384f)
                } else {
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(360 / 345f)
                },
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = pageItem.titleMsg),
                    style = WhatNowTheme.typography.headline2,
                    color = WhatNowTheme.colors.gray900,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = pageItem.description),
                    modifier = Modifier.padding(top = 10.dp),
                    style = WhatNowTheme.typography.body3,
                    color = WhatNowTheme.colors.gray900,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 800)
@Composable
fun SplashPageItemPreview() {
    WhatNowTheme {
        SplashPageItem(pageItem = PageItems.get(0), Modifier, true)
    }
}