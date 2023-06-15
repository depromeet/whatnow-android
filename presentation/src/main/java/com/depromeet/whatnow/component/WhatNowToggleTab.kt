package com.depromeet.whatnow.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.theme.White


@Composable
fun WhatNowToggleTab(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    @StringRes enabledTextRes: Int,
    @StringRes disabledTextRes: Int,
    onEnable: () -> Unit,
    onDisable: () -> Unit
) {
    Surface(
        modifier = modifier.then(Modifier.height(TOGGLE_TAB_HEIGHT)),
        color = White,
        shape = RoundedCornerShape(999.dp)
    ) {
        Row(
            modifier = Modifier.padding(6.dp)
        ) {

        }
    }
}

private val TOGGLE_TAB_HEIGHT = 49.dp
