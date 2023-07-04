package com.depromeet.whatnow.kakao

import android.content.Context

interface KakaoLogin {

    suspend fun login(context: Context): Result<KakaoAccessToken>

    suspend fun logout(): Result<Unit>
}
