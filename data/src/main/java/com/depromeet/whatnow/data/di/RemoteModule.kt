package com.depromeet.whatnow.data.di

import com.depromeet.whatnow.data.api.AccessTokenInterceptor
import com.depromeet.whatnow.data.api.ApiService
import com.depromeet.whatnow.data.api.AuthenticationListener
import com.depromeet.whatnow.data.api.Authenticator
import com.depromeet.whatnow.data.api.BaseUrl
import com.depromeet.whatnow.data.api.Interceptors
import com.depromeet.whatnow.data.api.RefreshApiService
import com.depromeet.whatnow.data.api.baseUrl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.depromeet.whatnow.data.provider.AccessTokenProvider
import com.depromeet.whatnow.data.provider.RefreshTokenProvider

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    @Provides
    @Singleton
    fun provideApiService(
        baseUrl: BaseUrl,
        interceptors: Interceptors,
    ): ApiService {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient(interceptors))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private fun createOkHttpClient(
        interceptors: Interceptors,
        apply: OkHttpClient.Builder.() -> Unit = { },
    ) = OkHttpClient.Builder()
        .apply {
            interceptors.interceptors.forEach(::addInterceptor)
        }
        .apply(apply)
        .build()
}
