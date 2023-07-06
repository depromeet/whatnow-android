package com.depromeet.whatnow.data.source

import android.content.SharedPreferences
import androidx.core.content.edit
import com.depromeet.whatnow.data.entity.JwtTokenEntity
import javax.inject.Inject

internal class AuthLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : AuthLocalDataSource {
    override suspend fun getJwtToken(): Result<JwtTokenEntity?> = runCatching {
        val accessToken = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
        val refreshToken = sharedPreferences.getString(KEY_REFRESH_TOKEN, null)
        if (accessToken == null || refreshToken == null) {
            return@runCatching null
        }
        JwtTokenEntity(accessToken = accessToken, refreshToken = refreshToken)
    }

    override suspend fun saveJwtToken(jwtToken: JwtTokenEntity): Result<Unit> = runCatching {
        sharedPreferences.edit {
            putString(KEY_ACCESS_TOKEN, jwtToken.accessToken)
            putString(KEY_REFRESH_TOKEN, jwtToken.refreshToken)
        }
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
        private const val KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN"
    }
}