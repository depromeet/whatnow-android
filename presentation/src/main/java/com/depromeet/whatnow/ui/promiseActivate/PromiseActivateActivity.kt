package com.depromeet.whatnow.ui.promiseActivate

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromiseActivateActivity : BaseActivity() {
    private val viewModel: PromiseActivateViewModel by viewModels()

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.promiseId.value = intent.getIntExtra("promiseId", -1)
        viewModel.getPromises()

        setContent {
            WhatNowTheme {
                val uiState by viewModel.uiState.collectAsState()
                val promise by viewModel.uiState.value.promise.collectAsState()

                if (promise != null) {
                    PromiseActivateScreen(
                        viewModel = viewModel,
                        onBack = ::finish,
                    )
                }

            }
        }
    }

    private fun navigateUp() = finish()

    companion object {
        fun startActivity(context: Context, promiseId: Int?) {
            val intent = Intent(context, PromiseActivateActivity::class.java)
            Log.d("ttt a", promiseId.toString())
            intent.putExtra("promiseId", promiseId)
            context.startActivity(intent)
        }
    }
}
