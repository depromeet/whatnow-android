package com.depromeet.whatnow.ui.splash

sealed interface SplashUiState {
    object Loading : SplashUiState

    object AlreadyLoggedIn : SplashUiState

    object NeedToOnboard : SplashUiState
}