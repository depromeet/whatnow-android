package com.depromeet.whatnow.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.theme.WhatNowTheme
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
            WhatNowToggleTabItem(
                enabled = enabled,
                textRes = enabledTextRes,
                onClick = onEnable
            )
            WhatNowToggleTabItem(
                enabled = enabled.not(),
                textRes = disabledTextRes,
                onClick = onDisable
            )
        }
    }
}

@Composable
private fun WhatNowToggleTabItem(
    enabled: Boolean,
    @StringRes textRes: Int,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(999.dp),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = if (enabled) WhatNowTheme.colors.whatNowPurple else White,
            contentColor = if (enabled) White else WhatNowTheme.colors.gray500
        )
    ) {
        Text(
            text = stringResource(id = textRes),
            style = WhatNowTheme.typography.body3
        )
    }
}

private val TOGGLE_TAB_HEIGHT = 49.dp
