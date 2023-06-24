package com.depromeet.whatnow.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WhatNowPromiseList(
    modifier: Modifier = Modifier,
    promises: List<String>
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(promises.size) {
            Column {
                WhatNowPromise(
                    modifier = modifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WhatNowPromiseListPreview() {
    WhatNowTheme {
        WhatNowPromiseList(Modifier, listOf("1", "2", "3"))
    }
}

