package com.depromeet.whatnow.domain.model

sealed interface LoginResult {

    object Signed : LoginResult

    data class NotSigned(
        val providerToken: ProviderToken
    ) : LoginResult
}
