package com.depromeet.whatnow.ui.promiseActivate

import com.depromeet.whatnow.domain.model.GetPromises
import com.depromeet.whatnow.domain.model.GetPromisesInteractions
import com.depromeet.whatnow.domain.model.InteractionsDetail
import com.depromeet.whatnow.domain.model.PromisesProgress
import com.depromeet.whatnow.ui.model.Promise
import kotlinx.coroutines.flow.MutableStateFlow

data class PromiseActivateState(
    val showTooltip: Boolean = false,
    val isAudioPlaying: Boolean = false,
    val selectedTab: PromiseActivateTab = PromiseActivateTab.All,
    val allProfile: List<Promise> = emptyList(),
    val myProfile: List<Promise> = emptyList(),
    val otherProfile: List<Promise> = emptyList(),

    val selectedEmojiTab: PromiseEmojiTab = PromiseEmojiTab.Music,
    val musicEmoji: List<Promise> = emptyList(),
    val poopEmoji: List<Promise> = emptyList(),
    val heartEmoji: List<Promise> = emptyList(),
    val footPrintEmoji: List<Promise> = emptyList(),

    var promise: GetPromises? = null,
    var promisesProgress: PromisesProgress? = null,
    var interactionsDetail: List<InteractionsDetail>? = null,
    var promisesInteractions: GetPromisesInteractions? = null,
    var timeOver: MutableStateFlow<Pair<String, String>> = MutableStateFlow<Pair<String, String>>(
        Pair(
            "0",
            "0"
        )
    )

)