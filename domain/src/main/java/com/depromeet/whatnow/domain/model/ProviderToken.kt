package com.depromeet.whatnow.domain.model

data class ProviderToken(
    val accessToken: String,
) {

    companion object {
        fun kakao(accessToken: String) = ProviderToken(
            accessToken = accessToken,
        )
    }
}
