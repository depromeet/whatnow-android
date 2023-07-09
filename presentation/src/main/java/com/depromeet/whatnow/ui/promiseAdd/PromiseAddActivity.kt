package com.depromeet.whatnow.ui.promiseAdd

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.ui.main.MainActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromiseAddActivity : BaseActivity() {
    private val viewModel: PromiseAddViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        var selectedTime by mutableStateOf(LocalTime.now())
        setContent {
            WhatNowTheme {
                val uiState by viewModel.uiState.collectAsState()
                uiState.let {
                    if (it == PromiseAddState.MakePromise) {
                        PromiseScreen(
                            viewModel = viewModel,
                            onBack = ::finish,
                        )
                    } else {
                        PromiseDetailScreen(
                            viewModel = viewModel,
                            goHomeClick = { startMainActivity() },
                            inviteClick = {}
                        )
                    }
                }

            }
        }
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    private fun navigateUp() = finish()

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, PromiseAddActivity::class.java)
            context.startActivity(intent)
        }
    }


}
