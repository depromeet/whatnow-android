package com.depromeet.whatnow.ui.archive

import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.ui.model.DUMMY_PROMISE
import com.depromeet.whatnow.ui.model.DUMMY_USER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor() : BaseViewModel() {

    private val _uiState = MutableStateFlow(ArchiveState())
    val uiState: StateFlow<ArchiveState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                futurePromises = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ),
                pastPromises = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                )
            )
        }
    }

    fun selectTab(tab: ArchiveTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }
}
