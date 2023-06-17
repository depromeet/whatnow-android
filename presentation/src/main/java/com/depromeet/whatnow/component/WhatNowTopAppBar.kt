package com.depromeet.whatnow.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.R

@Composable
fun WhatNowHomeAppBar() {
    Surface(
        modifier = Modifier.padding(all = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo),
            contentDescription = null,
            modifier = Modifier
                .width(114.dp)
                .height(36.dp)
        )
    }
}