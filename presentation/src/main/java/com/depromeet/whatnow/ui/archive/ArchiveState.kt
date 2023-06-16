package com.depromeet.whatnow.ui.archive

import com.depromeet.whatnow.ui.model.Promise

data class ArchiveState(
    val isLoading: Boolean = false,
    val selectedTab: ArchiveTab = ArchiveTab.Future,
    val futurePromises: List<Promise> = emptyList(),
    val pastPromises: List<Promise> = emptyList()
)
