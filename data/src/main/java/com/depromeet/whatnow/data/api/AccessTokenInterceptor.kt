package com.depromeet.whatnow.data.api

import com.depromeet.whatnow.data.provider.AccessTokenProvider
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AccessTokenInterceptor(
    private val accessTokenProvider: AccessTokenProvider,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = accessTokenProvider.value

        return if (accessToken.isBlank()) {
            chain.proceed(chain.request())
        } else {
            val request = from(chain.request(), accessToken)
            chain.proceed(request)
        }
    }

    companion object {
        private const val X_AUTH_TOKEN = "X-AUTH-TOKEN"

        fun from(request: Request, accessToken: String): Request = request.newBuilder()
            .removeHeader(X_AUTH_TOKEN)
            .addHeader(X_AUTH_TOKEN, accessToken)
            .build()
    }
}
