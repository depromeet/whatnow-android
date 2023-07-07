package com.depromeet.whatnow.ui.splashlogin

import android.util.Log
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
//    private val registerUseCase: RegisterUseCase,
) : BaseViewModel() {
    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.AppLoading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        launch {
            val job = launch { delay(MIN_SPLASH_TIME) }
            job.join()
            _uiState.value = SplashUiState.Onboarded
        }
    }

    fun login(accessToken: String) {
        if (job != null) {
            return
        }
        job = launch {
            loginUseCase(accessToken)
                .onSuccess { Log.d("yw", "로그인성공") }
                .onFailure { Log.d("yw", "로그인실패") }
        }
        job?.invokeOnCompletion { job = null }
    }

    companion object {
        private const val MIN_SPLASH_TIME = 2000L
    }
}