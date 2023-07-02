package com.depromeet.whatnow.ui.pictureView

import com.depromeet.whatnow.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PictureViewViewModel @Inject constructor() : BaseViewModel() {

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _uiState = MutableStateFlow(PictureViewState())
    val uiState: StateFlow<PictureViewState> = _uiState.asStateFlow()

    fun refresh() {
        _isRefresh.value = true
    }

    fun onRefresh() {
        _isRefresh.value = false
    }
}