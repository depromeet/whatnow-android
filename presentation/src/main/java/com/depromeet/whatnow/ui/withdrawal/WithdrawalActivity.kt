package com.depromeet.whatnow.ui.withdrawal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.ui.splashlogin.SplashActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WithdrawalActivity : BaseActivity() {

    private val viewModel: WithdrawViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhatNowTheme {
                WithdrawalScreen(
                    viewModel = viewModel,
                    gotoTitle = ::navigateToSplash,
                    onBack = ::finish
                )
            }
        }
    }

    private fun navigateToSplash() {
        SplashActivity.startActivity(this)
        finish()
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, WithdrawalActivity::class.java)
            context.startActivity(intent)
        }
    }
}