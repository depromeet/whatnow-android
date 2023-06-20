package com.depromeet.whatnow.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.model.Promise

@Composable
fun WhatNowTabOtherContent(
    promises: List<Promise>,
    onCreate: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(promises) {
            FuturePromiseTile(promise = it, onShare = {}, onDelete = {})
        }

    }
}