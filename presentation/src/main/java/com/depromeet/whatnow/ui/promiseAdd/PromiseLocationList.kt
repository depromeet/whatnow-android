package com.depromeet.whatnow.ui.promiseAdd

import android.text.Html
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.depromeet.whatnow.ui.theme.WhatNowPurple
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun PromiseLocationList(text:String ) {
    val pattern = "<b>(.*?)</b>".toRegex()
    val matches = pattern.findAll(text)

    var currentIndex = 0
    val annotatedString = buildAnnotatedString {
        matches.forEach { matchResult ->
            val range = matchResult.range
            val value = matchResult.groupValues[1]

            // 이전 일치하지 않는 텍스트 추가
            append(text.substring(currentIndex, range.first))

            // 일치하는 텍스트 추가 (흰색으로 스타일링)
            withStyle(style = SpanStyle(color = WhatNowPurple)) {
                append(value)
            }

            currentIndex = range.last + 1
        }

        // 나머지 일치하지 않는 텍스트 추가
        if (currentIndex < text.length) {
            append(text.substring(currentIndex, text.length))
        }
    }

    Text(
        text = annotatedString,
        style = WhatNowTheme.typography.body1,
    )
}

@Composable
@Preview
fun PromiseLocationListPreview() {
    PromiseLocationList(text = "<b>강남</b>구청")
}
