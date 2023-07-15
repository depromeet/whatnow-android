package com.depromeet.whatnow.ui.splashlogin

import android.content.Context
import android.content.Intent
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
import com.kakao.sdk.common.util.Utility
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
                Splash(visible = uiState == SplashUiState.AppLoading)
                SplashPage(
                    viewModel = viewModel,
                    visible = uiState == SplashUiState.Onboarded,
                    items = PageItems,
                    login = { requestKakaoLogin() }
                )

                uiState.let {
                    if (it == SplashUiState.RegisterAgree) {

                    } else if (it == SplashUiState.Signed) {
                        startMainActivity()
                    }

                }


            }
        }

    }

    private fun requestKakaoLogin() = lifecycleScope.launch {

        // 키 해쉬값
        val keyHash = Utility.getKeyHash(this@SplashActivity)

        kakaoLogin.login(this@SplashActivity)
            .onSuccess {
                viewModel.login(it.accessToken, it.id_token)
            }
            .onFailure { }
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
        }
    }
}
