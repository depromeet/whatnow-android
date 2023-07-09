package com.depromeet.whatnow.ui.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.depromeet.whatnow.component.KnowllyButtonDefaults
import com.depromeet.whatnow.component.WhatNowButton
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun RegisterAgreeDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        RegisterAgreeDialogContent(onDismiss)
    }
}

@Composable
private fun RegisterAgreeDialogContent(onDismiss: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 9.dp,
        color = WhatNowTheme.colors.gray50,
        contentColor = Color.Unspecified,
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.register_agree_title),
                modifier = Modifier.padding(start = 28.dp, top = 32.dp),
                style = WhatNowTheme.typography.headline3,
            )
            Text(
                text = stringResource(id = R.string.register_agree_msg),
                modifier = Modifier.padding(start = 28.dp, top = 32.dp),
                style = WhatNowTheme.typography.body3,
                textDecoration = TextDecoration.Underline
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                WhatNowButton(
                    buttonColors = KnowllyButtonDefaults.textGrayButtonColors,
                    text = stringResource(id = R.string.register_agree_btn),
                    onClick = { onDismiss() },
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .width(200.dp)
                )
            }
        }
    }
}

@Preview(widthDp = 360)
@Composable
private fun RegisterAgreeDialogPreview() {
    WhatNowTheme {
        RegisterAgreeDialogContent(onDismiss = { })
    }
}
