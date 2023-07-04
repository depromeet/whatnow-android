package com.depromeet.whatnow.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.model.Promise

@Composable
fun WhatNowTabAllContentGrid(
    promise: Promise,
    modifier: Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            WhatNowProfile(promise = promise, profileImageSize = 72.dp, statusImageSize = 104.dp)
            Text(text = promise.participants[0].name, modifier = modifier.padding())
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            WhatNowProfile(promise = promise,profileImageSize = 72.dp, statusImageSize = 104.dp)
            Text(text = promise.participants[0].name, modifier = modifier.padding())

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WhatNowProfile(promise = promise,profileImageSize = 72.dp, statusImageSize = 104.dp)
            Text(text = promise.participants[0].name, modifier = modifier.padding())

        }


    }

}
