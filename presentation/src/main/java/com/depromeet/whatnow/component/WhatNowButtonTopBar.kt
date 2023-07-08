package com.depromeet.whatnow.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowButtonTopBar(
    modifier: Modifier = Modifier,
    @DrawableRes backIconRes: Int = R.drawable.ic_back,
    onBack: () -> Unit,
    @StringRes titleRes: Int,
    @StringRes buttonTextRes: Int,
    onClickButton: () -> Unit
) {
    Surface(
        modifier = modifier.then(
            Modifier
                .height(SIMPLE_TOP_BAR_HEIGHT)
                .fillMaxWidth()
        ),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            WhatNowIconButton(
                iconRes = backIconRes,
                tint = WhatNowTheme.colors.whatNowBlack,
                size = 24.dp,
                onClick = onBack
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(id = titleRes),
                style = WhatNowTheme.typography.headline2,
                color = WhatNowTheme.colors.whatNowBlack
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onClickButton,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = stringResource(id = buttonTextRes),
                    style = WhatNowTheme.typography.body3
                )
            }
        }
    }
}

private val SIMPLE_TOP_BAR_HEIGHT = 64.dp
