package com.depromeet.whatnow.ui.home

import com.depromeet.whatnow.domain.model.GetPromisesUsersStatus
import kotlinx.coroutines.flow.MutableStateFlow

data class HomeState(
    val showTooltip: Boolean = false,
    val isAudioPlaying: Boolean = false,
    var currentStatus: MutableStateFlow<HomeActivateStatus> = MutableStateFlow(HomeActivateStatus.InActivity),
    var promisesUsersStatus: MutableStateFlow<List<GetPromisesUsersStatus>> = MutableStateFlow(
        emptyList()
    ),
    var timeOver: MutableStateFlow<Pair<String, String>> = MutableStateFlow<Pair<String, String>>(
        Pair(
            "0",
            "0"
        )
    )
)