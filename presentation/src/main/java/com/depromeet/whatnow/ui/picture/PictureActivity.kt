package com.depromeet.whatnow.ui.picture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.depromeet.whatnow.base.BaseActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PictureActivity : BaseActivity() {
    private val viewModel: PictureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhatNowTheme {
                PictureScreen(
                    viewModel = viewModel,
                )
            }
        }
    }

    private fun navigateUp() = finish()

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, PictureActivity::class.java)
            context.startActivity(intent)
        }
    }
}
