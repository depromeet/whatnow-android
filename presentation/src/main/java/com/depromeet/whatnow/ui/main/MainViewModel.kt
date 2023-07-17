package com.depromeet.whatnow.ui.main

import android.util.Log
import android.webkit.CookieManager
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.usecase.GetJwtTokenUseCase
import com.depromeet.whatnow.domain.usecase.GetPromisesUsersStatusUseCase
import com.depromeet.whatnow.domain.usecase.PostUsersJoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getJwtTokenUseCase: GetJwtTokenUseCase,
    private val getPromisesUsersStatusUseCase: GetPromisesUsersStatusUseCase,
//    private val getPromisesActiveUseCase: GetPromisesActiveUseCase,
    private val postUsersJoinUseCase: PostUsersJoinUseCase,
) : BaseViewModel() {

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    init {
        manageCookie()
        getPromisesUsersStatus()
    }

    private fun manageCookie() {
        launch {
            val token = getJwtTokenUseCase().getOrThrow().accessToken

            val cookieManager = CookieManager.getInstance()
            cookieManager.removeAllCookies {}
            cookieManager.setAcceptCookie(true)

            cookieManager.setCookie("https://dev.whatnow.kr", "access-token=$token;")
            cookieManager.flush()
        }
    }

    fun getPromisesUsersStatus() {
        launch {
            getPromisesUsersStatusUseCase(status = "BEFORE").onSuccess {
                _uiState.value.promisesUsersStatus.value = it
            }.onFailure { Log.d("ttt onFailure", it.toString()) }
        }

    }

    fun postUserJoin(inviteCode: String) {
        launch {
            postUsersJoinUseCase(inviteCode)
                .onSuccess {
                    getPromisesUsersStatus()
                    Log.d("yw", "성공 $it")
                }
                .onFailure { Log.d("yw", "실패 $it") }
        }
    }

    fun refresh() {
        _isRefresh.value = true
    }

    fun onRefresh() {
        _isRefresh.value = false
    }
}
