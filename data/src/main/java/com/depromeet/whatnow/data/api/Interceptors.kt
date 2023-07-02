package com.depromeet.whatnow.data.api

import okhttp3.Interceptor

data class Interceptors(
    val interceptors: List<Interceptor>,
) {

    companion object {
        val Empty = Interceptors(emptyList())
    }
}
