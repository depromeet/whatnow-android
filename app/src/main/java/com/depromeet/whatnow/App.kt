package com.depromeet.whatnow

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "4416983cab300794e7e97eef97768746")
    }
}
