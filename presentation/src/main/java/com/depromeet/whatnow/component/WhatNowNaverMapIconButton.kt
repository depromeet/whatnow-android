package com.depromeet.whatnow.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.theme.WhatNowTheme


@Composable
fun WhatNowNaverMapIconButton(
    modifier: Modifier,
    @DrawableRes iconButtonRes: Int,
    padding: PaddingValues,
    alignment: Alignment,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)

    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier
                .align(alignment = alignment)
                .clip(shape = RoundedCornerShape(16.dp))
                .size(40.dp)
                .background(WhatNowTheme.colors.whatNowBlack)
        ) {
            Icon(
                painter = painterResource(id = iconButtonRes),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}