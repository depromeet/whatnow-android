package com.depromeet.whatnow.ui.archive.detail

import androidx.lifecycle.SavedStateHandle
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.ui.model.Promise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val promises = requireNotNull(
        savedStateHandle.get<List<Promise>>(DetailActivity.EXTRA_KEY_PROMISES)
    )
    private val currentIndex = requireNotNull(
        savedStateHandle.get<Int>(DetailActivity.EXTRA_KEY_INDEX)
    )

    private val _uiState = MutableStateFlow(
        DetailState(
            promises = promises,
            currentIndex = currentIndex
        )
    )
    val uiState: StateFlow<DetailState> = _uiState.asStateFlow()

    fun increaseIndex() {
        val currentIndex = _uiState.value.currentIndex
        if (currentIndex == _uiState.value.promises.lastIndex) return
        _uiState.update { it.copy(currentIndex = currentIndex + 1) }
    }

    fun decreaseIndex() {
        val currentIndex = _uiState.value.currentIndex
        if (currentIndex == 0) return
        _uiState.update { it.copy(currentIndex = currentIndex - 1) }
    }
}
