package com.depromeet.whatnow.ui.main

import com.depromeet.whatnow.domain.model.GetPromisesUsersStatus
import kotlinx.coroutines.flow.MutableStateFlow

data class MainState(
    val showTooltip: Boolean = false,
    val isAudioPlaying: Boolean = false,
    var promisesUsersStatus: MutableStateFlow<List<GetPromisesUsersStatus>> = MutableStateFlow(
        emptyList()
    ),
)