package com.depromeet.whatnow.ui.promiseActivate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromiseActivateActivity : BaseActivity() {
    private val viewModel: PromiseActivateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.promiseId.value = intent.getIntExtra("promiseId", -1)

        setContent {
            WhatNowTheme {
                PromiseActivateScreen(
                    viewModel = viewModel,
                    onBack = ::finish,
                )
            }
        }
    }

    private fun navigateUp() = finish()

    companion object {
        fun startActivity(context: Context, promiseId: Int?) {
            val intent = Intent(context, PromiseActivateActivity::class.java)
            intent.putExtra("promiseId", promiseId)
            context.startActivity(intent)
        }
    }
}
