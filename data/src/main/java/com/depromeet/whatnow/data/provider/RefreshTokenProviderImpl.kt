package com.depromeet.whatnow.data.provider

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

internal class RefreshTokenProviderImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : RefreshTokenProvider {

    override var value: String
        get() = sharedPreferences.getString(KEY_REFRESH_TOKEN, null) ?: ""
        set(value) {
            sharedPreferences.edit(true) {
                putString(KEY_REFRESH_TOKEN, value)
            }
        }

    companion object {
        private const val KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN"
    }
}
