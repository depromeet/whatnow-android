package com.depromeet.whatnow.ui.archive.detail

import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.ui.model.DUMMY_PROMISE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : BaseViewModel() {

    private val _uiState = MutableStateFlow(DetailState(selectedPromise = DUMMY_PROMISE()))
    val uiState: StateFlow<DetailState> = _uiState.asStateFlow()

}
