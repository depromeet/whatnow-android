package com.depromeet.whatnow.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.ui.splashlogin.SplashActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.depromeet.whatnow.ui.withdrawal.WithdrawalActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BaseActivity() {

    private val viewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhatNowTheme {
                SettingScreen(
                    viewModel = viewModel,
                    navigateToWithdrawal = ::navigateToWithdrawal,
                    navigateToSplash = ::navigateToSplash,
                    onBack = ::finish
                )
            }
        }
    }

    private fun navigateToWithdrawal() = WithdrawalActivity.startActivity(this)

    private fun navigateToSplash() {
        SplashActivity.startActivity(this)
        finish()
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SettingActivity::class.java)
            context.startActivity(intent)
        }
    }
}