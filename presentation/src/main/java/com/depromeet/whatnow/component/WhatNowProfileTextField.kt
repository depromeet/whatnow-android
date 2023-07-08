package com.depromeet.whatnow.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.depromeet.whatnow.ui.theme.White

@Composable
fun WhatNowProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit
) {
    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        textStyle = WhatNowTheme.typography.body1,
        onValueChange = onValueChange,
        singleLine = true,
        decorationBox = { innerTextField ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(59.dp),
                color = White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.width(24.dp))
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.profile_textfield_placeholder),
                                style = WhatNowTheme.typography.body1,
                                color = WhatNowTheme.colors.gray400
                            )
                        }
                        innerTextField()
                    }
                    WhatNowIconButton(
                        iconRes = R.drawable.ic_clear,
                        tint = if (value.isEmpty()) {
                            WhatNowTheme.colors.gray400
                        } else {
                            WhatNowTheme.colors.whatNowBlack
                        },
                        size = 24.dp,
                        onClick = onClear
                    )
                }
            }
        }
    )
}
