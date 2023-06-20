package com.depromeet.whatnow.ui.highlight

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HighlightActivity : ComponentActivity() {

    private val viewModel: HighlightViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhatNowTheme {
                HighlightScreen(
                    viewModel = viewModel,
                    onBack = ::finish
                )
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, HighlightActivity::class.java)
            context.startActivity(intent)
        }
    }
}
