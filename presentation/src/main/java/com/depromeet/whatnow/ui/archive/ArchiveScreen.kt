package com.depromeet.whatnow.ui.archive

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.component.FuturePromiseTile
import com.depromeet.whatnow.component.PastPromiseCard
import com.depromeet.whatnow.component.WhatNowSimpleTopBar
import com.depromeet.whatnow.component.WhatNowToggleTab
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun ArchiveScreen(
    viewModel: ArchiveViewModel,
    onBack: () -> Unit,
    navigateToDetail: (List<Promise>, Int) -> Unit,
    navigateToPromiseAdd: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var isCalendarView by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            WhatNowSimpleTopBar(
                onBack = { if (isCalendarView) isCalendarView = false else onBack() },
                titleRes = R.string.promise_archive,
                actionIconRes = if (!isCalendarView) R.drawable.ic_calendar else null,
                onAction = { isCalendarView = true }
            )
        }
    ) { innerPadding ->
        if (isCalendarView) {
            CalendarScreen(
                modifier = Modifier.padding(innerPadding),
                promises = (uiState.pastPromises + uiState.futurePromises),
                onClickItem = navigateToDetail
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WhatNowToggleTab(
                    enabled = uiState.selectedTab == ArchiveTab.Future,
                    enabledTextRes = R.string.future_promise,
                    disabledTextRes = R.string.past_promise,
                    onEnable = { viewModel.selectTab(ArchiveTab.Future) },
                    onDisable = { viewModel.selectTab(ArchiveTab.Past) }
                )
                when (uiState.selectedTab) {
                    ArchiveTab.Future -> {
                        FuturePromiseContent(
                            promises = uiState.futurePromises,
                            onCreate = { navigateToPromiseAdd() })
                    }

                    ArchiveTab.Past -> {
                        PastPromiseContent(
                            promises = uiState.pastPromises,
                            onCreate = { navigateToPromiseAdd() },
                            onClickItem = navigateToDetail
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FuturePromiseContent(
    promises: List<Promise>,
    onCreate: () -> Unit,
) {
    if (promises.isEmpty()) {
        EmptyArchiveContent(tab = ArchiveTab.Future, onCreate = onCreate)
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(promises) {
                FuturePromiseTile(promise = it, onShare = {}, onDelete = {})
            }
        }
    }
}

@Composable
fun PastPromiseContent(
    promises: List<Promise>,
    onCreate: () -> Unit,
    onClickItem: (List<Promise>, Int) -> Unit,
) {
    if (promises.isEmpty()) {
        EmptyArchiveContent(tab = ArchiveTab.Past, onCreate = onCreate)
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(promises.size) {
                PastPromiseCard(promise = promises[it], onClick = { onClickItem(promises, it) })
            }
        }
    }
}

@Composable
private fun EmptyArchiveContent(
    tab: ArchiveTab,
    onCreate: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(
                    id = when (tab) {
                        ArchiveTab.Future -> R.string.future_promise_empty_title
                        ArchiveTab.Past -> R.string.past_promise_empty_title
                    }
                ),
                style = WhatNowTheme.typography.body1,
                color = WhatNowTheme.colors.gray600,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(
                    id = when (tab) {
                        ArchiveTab.Future -> R.string.future_promise_empty_subtitle
                        ArchiveTab.Past -> R.string.past_promise_empty_subtitle
                    }
                ),
                style = WhatNowTheme.typography.body1,
                color = WhatNowTheme.colors.gray500,
                textAlign = TextAlign.Center
            )
        }
        Image(
            painter = painterResource(id = R.drawable.img_future_promise_empty),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onCreate,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = stringResource(id = R.string.go_to_make_promise),
                style = WhatNowTheme.typography.body1
            )
        }
    }
}