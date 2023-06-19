package com.depromeet.whatnow.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.promiseActivate.PromiseActivateTab
import com.depromeet.whatnow.ui.theme.WhatNowTheme


@Composable
fun WhatNowTab(
    modifier: Modifier = Modifier,
    selected: PromiseActivateTab,
    onTotalClicked: () -> Unit,
    onMeClicked: () -> Unit,
    onFriendClicked: () -> Unit

) {
    Row() {
        WhatNowTabItem(
            modifier = modifier,
            enabled = selected == PromiseActivateTab.All,
            textRes = R.string.all_title,
            onClick = onTotalClicked
        )
        WhatNowTabItem(
            modifier = modifier,
            enabled = selected == PromiseActivateTab.My,
            textRes = R.string.my_title,
            onClick = onMeClicked
        )
        WhatNowTabItem(
            modifier = modifier,
            enabled = selected == PromiseActivateTab.Other,
            textRes = R.string.other_title,
            onClick = onFriendClicked
        )

    }
}

@Composable
private fun WhatNowTabItem(
    modifier : Modifier,
    enabled: Boolean,
    @StringRes textRes: Int,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier
            .background(WhatNowTheme.colors.whatNowBlack)
            .padding(end = 20.dp),
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                modifier = modifier
                    .width(40.dp)
                    .background(WhatNowTheme.colors.whatNowBlack)
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Center,
                text = stringResource(id = textRes),
                style = if (enabled) WhatNowTheme.typography.body2.copy(
                    fontSize = 16.sp,
                    color = WhatNowTheme.colors.gray50
                )
                else WhatNowTheme.typography.caption1.copy(
                    fontSize = 16.sp,
                    color = WhatNowTheme.colors.gray600
                )
            )
            if (enabled) Divider(
                color = WhatNowTheme.colors.gray50,
                modifier = modifier
                    .width(40.dp)
                    .height(2.dp)
            )
            else Divider(
                color = WhatNowTheme.colors.whatNowBlack,
                modifier = modifier
                    .width(40.dp)
                    .height(2.dp)
            )
        }
    }
}