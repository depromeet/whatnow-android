package com.depromeet.whatnow.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.component.KnowllyButtonDefaults.textGrayButtonColors
import com.depromeet.whatnow.component.KnowllyButtonDefaults.textStyle
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowButton(
    buttonColors: ButtonColors,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp),
    buttonSize: Int = 45
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(buttonSize.dp),
        enabled = enabled,
        shape = KnowllyButtonDefaults.ButtonShape,
        colors = buttonColors,
        contentPadding = contentPadding,
    ) {
        Text(
            style = textStyle,
            text = text,
        )
    }
}

object KnowllyButtonDefaults {
    val ButtonHeight = 56.dp
    val ButtonShape = RoundedCornerShape(16.dp)
    val textStyle
        @Composable
        get() = WhatNowTheme.typography.body1

    val textGrayButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = WhatNowTheme.colors.whatNowPurple,
            contentColor = WhatNowTheme.colors.gray50,
            disabledContainerColor = WhatNowTheme.colors.gray200,
            disabledContentColor = WhatNowTheme.colors.gray900
        )
    val textPurpleButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = WhatNowTheme.colors.gray200,
            contentColor = WhatNowTheme.colors.whatNowPurple,
            disabledContainerColor = WhatNowTheme.colors.gray200,
            disabledContentColor = WhatNowTheme.colors.gray900
        )
    val backBlackButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = WhatNowTheme.colors.whatNowBlack,
            contentColor = Color.White,
            disabledContainerColor = WhatNowTheme.colors.gray200,
            disabledContentColor = WhatNowTheme.colors.gray900
        )

}

@Preview("Enabled")
@Composable
private fun KnowllyContainedButtonPreviewEnabled() {
    WhatNowTheme {
        WhatNowButton(
            buttonColors = textGrayButtonColors,
            text = "버튼",
            onClick = { },
            enabled = true,
        )
    }
}