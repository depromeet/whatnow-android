package com.depromeet.whatnow.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.R

@Composable
fun WhatNowHomeAppBar(modifier: Modifier) {
    Image(
        modifier = modifier.padding(start = 16.dp, top = 19.25.dp, bottom = 19.3.dp),
        painter = painterResource(id = R.drawable.whatnow_title),
        contentDescription = null,
    )
}