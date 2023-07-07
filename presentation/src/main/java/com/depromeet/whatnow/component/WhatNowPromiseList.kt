package com.depromeet.whatnow.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.domain.model.GetPromisesUsersStatus

@Composable
fun WhatNowPromiseList(
    modifier: Modifier = Modifier,
    promisesUsersStatus: List<GetPromisesUsersStatus>
) {
    LazyColumn(
        modifier = modifier.height(((promisesUsersStatus.size * 80) + 80).dp),
        userScrollEnabled = false
    ) {
        items(promisesUsersStatus.size) {
            Column {
                WhatNowPromise(
                    modifier = modifier, promisesUsersStatusItem = promisesUsersStatus[it]
                )
            }
        }
    }
}

