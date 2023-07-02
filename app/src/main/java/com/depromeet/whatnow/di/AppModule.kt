package com.depromeet.whatnow.di

import com.depromeet.whatnow.BuildConfig
import com.depromeet.whatnow.data.api.BaseUrl
import com.depromeet.whatnow.data.api.Interceptors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    fun provideBaseUrl(): BaseUrl = BaseUrl("https://dev.whatnow.kr")

    @Provides
    fun provideInterceptors(): Interceptors {
        return if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor.Logger { message ->
//                Logger.d("OkHttp", message)
            }
            val loggingInterceptor = HttpLoggingInterceptor(logger)
                .apply { level = HttpLoggingInterceptor.Level.BODY }
            Interceptors(
                listOf(loggingInterceptor)
            )
        } else {
            Interceptors.Empty
        }
    }
}