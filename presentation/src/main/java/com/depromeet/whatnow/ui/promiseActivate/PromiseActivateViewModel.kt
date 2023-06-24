package com.depromeet.whatnow.ui.promiseActivate

import com.depromeet.whatnow.ui.base.BaseViewModel
import com.depromeet.whatnow.ui.model.DUMMY_PROMISE
import com.depromeet.whatnow.ui.model.DUMMY_USER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PromiseActivateViewModel @Inject constructor() : BaseViewModel() {

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _uiState = MutableStateFlow(PromiseActivateState())
    val uiState: StateFlow<PromiseActivateState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                allProfile = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ), myProfile = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ), otherProfile = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ),

                musicEmoji = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ), poopEmoji = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ), heartEmoji = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ), footPrintEmoji = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                )
            )
        }
    }

    fun selectTab(tab: PromiseActivateTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    fun selectEmojiTab(tab: PromiseEmojiTab) {
        _uiState.update { it.copy(selectedEmojiTab = tab) }
    }

    fun refresh() {
        _isRefresh.value = true
    }

    fun onRefresh() {
        _isRefresh.value = false
    }
}