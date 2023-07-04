package com.depromeet.whatnow.ui.splashlogin

sealed interface SplashUiState {
    object AppLoading : SplashUiState

    object Onboarded : SplashUiState
}