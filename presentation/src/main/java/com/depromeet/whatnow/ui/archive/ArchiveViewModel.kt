package com.depromeet.whatnow.ui.archive

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ArchiveState())
    val uiState: StateFlow<ArchiveState> = _uiState.asStateFlow()
}
