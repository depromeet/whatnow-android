package com.depromeet.whatnow.ui.highlight

import androidx.lifecycle.SavedStateHandle
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.ui.model.Highlight
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HighlightViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val highlights = requireNotNull(
        savedStateHandle.get<List<Highlight>>(HighlightActivity.EXTRA_KEY_HIGHLIGHTS)
    )

    private val _uiState = MutableStateFlow(HighlightState(highlights = highlights))
    val uiState: StateFlow<HighlightState> = _uiState.asStateFlow()
}
