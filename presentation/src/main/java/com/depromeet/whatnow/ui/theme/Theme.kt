package com.depromeet.whatnow.ui.theme

import android.app.Activity
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

@Composable
fun WhatNowTheme(
    colors: WhatNowColors = WhatNowTheme.colors,
    typography: WhatNowTypography = WhatNowTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember { colors.copy() }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = MaterialColors.background.toArgb()
        }
    }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides typography,
        LocalContentColor provides White
    ) {
        MaterialTheme(
            colorScheme = MaterialColors,
            content = content
        )
    }
}

object WhatNowTheme {

    val colors: WhatNowColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: WhatNowTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}
