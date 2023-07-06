package com.depromeet.whatnow.feature

import com.depromeet.whatnow.kakao.KakaoLogin
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class KakaoModule {

    @Binds
    abstract fun bindsKakaoLogin(kakaoLogin: KakaoLoginImpl): KakaoLogin
}
