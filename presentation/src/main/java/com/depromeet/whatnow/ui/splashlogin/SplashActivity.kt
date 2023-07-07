package com.depromeet.whatnow.ui.splashlogin

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.kakao.KakaoLogin
import com.depromeet.whatnow.ui.main.MainActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private val viewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var kakaoLogin: KakaoLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhatNowTheme {
                val uiState by viewModel.uiState.collectAsState()

                Splash(visible = uiState != SplashUiState.Onboarded)
                SplashPage(
                    visible = uiState == SplashUiState.Onboarded,
                    items = PageItems,
                    login = { requestKakaoLogin() },
                )
            }
        }

    }

    private fun requestKakaoLogin() = lifecycleScope.launch {
        kakaoLogin.login(this@SplashActivity)
            .onSuccess {
                viewModel.login(it.value)
            }
            .onFailure {
            }
    }


    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }


}