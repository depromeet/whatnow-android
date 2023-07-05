package com.depromeet.whatnow.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowTimeOverBar(modifier: Modifier) {
    Surface(
        modifier = modifier
            .width(139.dp)
            .height(48.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.background(WhatNowTheme.colors.whatNowError),
            ) {
            Text(
                text = stringResource(R.string.time_over),
                style = WhatNowTheme.typography.headline2.copy(
                    fontSize = 22.sp, color = Color.White, textAlign = TextAlign.Center
                )
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun WhatNowTimeOverBarPreview() {
    WhatNowTheme {
        WhatNowTimeOverBar(modifier = Modifier)
    }
}
