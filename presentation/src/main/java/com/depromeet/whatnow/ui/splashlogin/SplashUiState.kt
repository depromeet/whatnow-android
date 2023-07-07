package com.depromeet.whatnow.ui.splashlogin

sealed interface SplashUiState {
    // 앱 처음시작 로고
    object AppLoading : SplashUiState

    // 온보딩 화면
    object Onboarded : SplashUiState

    // 회원가입 안했을때 약간 동의 팝업 생성
    object RegisterAgree : SplashUiState

    // 로그인성공 -> Main으로 이동
    object Signed : SplashUiState
}