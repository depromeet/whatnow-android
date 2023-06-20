package com.depromeet.whatnow.ui.promiseAdd

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromiseAddActivity : BaseActivity() {
    private val viewModel: PromiseAddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        var selectedTime by mutableStateOf(LocalTime.now())

        setContent {
            WhatNowTheme {
                PromiseScreen(
                    onBack = {}
//                    viewModel = viewModel,
                )
            }
        }
    }

    private fun navigateUp() = finish()

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, PromiseAddActivity::class.java)
            context.startActivity(intent)
        }
    }
}
