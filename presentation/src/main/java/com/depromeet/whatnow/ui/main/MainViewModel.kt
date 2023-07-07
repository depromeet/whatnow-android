package com.depromeet.whatnow.ui.main

import android.webkit.CookieManager
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.usecase.GetJwtTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getJwtTokenUseCase: GetJwtTokenUseCase
) : BaseViewModel() {
    init {
        manageCookie()
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
}
