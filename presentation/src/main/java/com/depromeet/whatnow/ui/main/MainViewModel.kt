package com.depromeet.whatnow.ui.main

import android.util.Log
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.usecase.GetPromisesUsersStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPromisesUsersStatusUseCase: GetPromisesUsersStatusUseCase
) : BaseViewModel() {

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    init {
        getPromisesUsersStatus()
    }

    fun getPromisesUsersStatus() {
        launch {
            getPromisesUsersStatusUseCase(status = "BEFORE").onSuccess {
                _uiState.value.promisesUsersStatus = it.contest

            }.onFailure { Log.d("ttt onFailure", it.toString()) }
        }

    }

    fun refresh() {
        _isRefresh.value = true
    }

    fun onRefresh() {
        _isRefresh.value = false
    }
}