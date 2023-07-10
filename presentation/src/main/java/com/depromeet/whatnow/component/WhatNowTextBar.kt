package com.depromeet.whatnow.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowTextBar(
    modifier: Modifier = Modifier,
    @StringRes textRes: Int,
    @StringRes subtextRes: Int,
) {
    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = textRes),
            style = WhatNowTheme.typography.body1,
            color = WhatNowTheme.colors.whatNowBlack
        )
        Text(
            text = stringResource(id = subtextRes),
            style = WhatNowTheme.typography.caption1,
            color = WhatNowTheme.colors.gray600
        )
    }
}
