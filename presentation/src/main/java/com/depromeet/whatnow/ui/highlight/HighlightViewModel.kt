package com.depromeet.whatnow.ui.highlight

import androidx.lifecycle.ViewModel
import com.depromeet.whatnow.ui.model.DUMMY_HIGHLIGHT
import com.depromeet.whatnow.ui.model.HighlightType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HighlightViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HighlightState())
    val uiState: StateFlow<HighlightState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                highlights = listOf(
                    DUMMY_HIGHLIGHT(),
                    DUMMY_HIGHLIGHT(type = HighlightType.Arrive, text = "윤여준 도착완료"),
                    DUMMY_HIGHLIGHT(),
                    DUMMY_HIGHLIGHT(type = HighlightType.Arrive, text = "윤여준 도착완료"),
                    DUMMY_HIGHLIGHT(),
                )
            )
        }
    }
}
